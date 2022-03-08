/*
CSE 109: Fall 2017
Jake Schinto
jjs220
Basic Network Server
Program #8
*/

#include<iostream>
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<fcntl.h>
#include<sys/unistd.h>
#include<errno.h>
#include<openssl/evp.h>

using namespace std;

void connectionMade(char* filename, int &s0, int &s1, struct sockaddr_in &peeraddr, socklen_t &peeraddr_len);
void createFile(int port, char* filename);
char* checksum(char *check, size_t size);

int main(int argc, char** argv)
{
	if(argc != 2)
	{
		cerr << "must use 2 arguments" << endl;
		return 1;
	}

	int port = 10000;
	char* filename = argv[1];
	cout << filename << endl;

	while(port <= 10500)
	{
		int s0 = socket(AF_INET, SOCK_STREAM, 0);
		if(s0 >= 0)
		{
			struct sockaddr_in myaddr;
			memset(&myaddr, 0, sizeof(struct sockaddr_in));
			myaddr.sin_family = AF_INET;
			myaddr.sin_port = htons(port);
			myaddr.sin_addr.s_addr = htonl(INADDR_ANY);

			int res = bind(s0, (struct sockaddr*) &myaddr, sizeof(myaddr));
			if(res >= 0)
			{
				struct linger linger_opt = {1, 0};
				setsockopt(s0, SOL_SOCKET, SO_LINGER, &linger_opt, sizeof(linger_opt));

				res = listen(s0, 1);
				if(res >= 0)
				{
					createFile(port, filename);
					if(errno != 0)
					{
						cerr << strerror(errno) << endl;
					}
					struct sockaddr_in peeraddr;
					socklen_t peeraddr_len;
					int s1 = accept(s0, (struct sockaddr*) &peeraddr, &peeraddr_len);
					if(s1 >= 0)
					{
						cout << "connection accepted" << endl;
						connectionMade(filename, s0, s1, peeraddr, peeraddr_len);
						return 0;
					}
				}
				else
				{
					cerr << "Cannot listen" << endl;
				}
			}
			else
			{
				//cerr << "Cannot Bind to socket" << endl;
			}
		}
		else
		{
			cerr << "Cannot create a socket" << endl;
		}
		//cout << "port " << port << " failed" << endl;
		port++;
	}

	cerr << "failed to find a port" << endl;
	return 2;
}

void connectionMade(char* filename, int &s0, int &s1, struct sockaddr_in &peeraddr, socklen_t &peeraddr_len)
{
	cout << "MADE IT" << endl;
	long long dataSize;
	read(s1, (void *)&dataSize, 8);
	long long starting;
	read(s1, (void *)&starting, 8);
	long long requested;
	read(s1, (void *)&requested, 8);
	char check[dataSize-23];
	check[dataSize-23] = '\0';
	read(s1, (void *)check, dataSize-24);

	int file = open(filename, O_RDWR);
	lseek(file, starting, SEEK_SET);
	char data[requested+1];
	data[requested] = '\0';
	read(file, (void *)data, requested);
	close(file);

	char* checkSum = checksum(check, dataSize-23);
	int total = strlen(data) + strlen(checkSum) + 16;
	write(s1, (void *)&total, 8);

	int checklen = strlen(checkSum);
	write(s1, (void *)&checklen, 8);
	write(s1, (void *)checkSum, strlen(checkSum));
	free(checkSum);

	write(s1, (void *)data, strlen(data));
	cout << starting << " : " << requested << endl;
	if(starting != 0 || requested != 0)
	{
		//close(s1);
		//listen(s0, 1);
		cerr << strerror(errno) << endl;
		s1 = accept(s0, (struct sockaddr*) &peeraddr, &peeraddr_len);
		if(s1 >= 0)
		{
			connectionMade(filename, s0, s1, peeraddr, peeraddr_len);
		}
		else
		{
			close(s0);
			close(s1);
		}
	}
	else
	{
		close(s0);
		close(s1);
	}
}

void createFile(int port, char* filename)
{
	errno = 0;
	char hostname[1024];
	gethostname(hostname, 1023);
	long long hostnameSize = strlen(hostname);
	long long filenameSize = strlen(filename);

	int file = open("connection.txt", O_CREAT|O_RDWR|O_TRUNC, 0666);
	write(file, (void *)&port, 2);
	write(file, (void *)&hostnameSize, 8);
	write(file, (void *)hostname, hostnameSize);
	write(file, (void *)&filenameSize, 8);
	write(file, (void *)filename, filenameSize);
	close(file);
}

char* checksum(char *data, size_t size)
{
	EVP_MD_CTX *mdctx;
    const EVP_MD *md;
    unsigned char md_value[EVP_MAX_MD_SIZE];
    unsigned int md_len;

    OpenSSL_add_all_digests();

    md = EVP_get_digestbyname("sha1");

    mdctx = EVP_MD_CTX_create();
    EVP_DigestInit_ex(mdctx, md, NULL);
    EVP_DigestUpdate(mdctx, data, size);
    EVP_DigestFinal_ex(mdctx, md_value, &md_len);
    EVP_MD_CTX_destroy(mdctx);

	char* md_valueNew = (char *)malloc(md_len + 1);
	memcpy(md_valueNew, md_value, md_len);
    md_valueNew[md_len] = '\0';
	return md_valueNew;
}
