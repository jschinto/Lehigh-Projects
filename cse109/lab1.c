//group 12: Jake Schinto,Onur Gencer Ates, Komal Grover
#include<stdio.h>

int main(int argc, char **argsc){
	char colon = ':';
	char word[11] = "applesauce";
	for(int i = 1; i <= 100; i++){
		for(int j = 0; j < 10; j++){
			if(i%10 == 1){
				fprintf(stdout, "%3i", j);
				fprintf(stdout, "%c ", colon);
				fprintf(stdout, "%12i", (i*j)+(7500*(i-50)));
			}
			if(i%10 == 2){
                fprintf(stdout, "%-3i", j);
                fprintf(stdout, "%-c ", colon);
                fprintf(stdout, "%-12i", (i*j)+(7500*(i-50)));
            }
			if(i%10 == 3){
                fprintf(stdout, "%+3i", j);
                fprintf(stdout, "%c ", colon);
                fprintf(stdout, "%+12i", (i*j)+(7500*(i-50)));
            }
			if(i%10 == 4){
                fprintf(stdout, "%+3i", j);
                fprintf(stdout, "%c ", colon);
                fprintf(stdout, "%+12hi", (i*j)+(7500*(i-50)));
            }
			if(i%10 == 5){
                fprintf(stdout, "%3i", j);
                fprintf(stdout, "%c ", colon);
                fprintf(stdout, "%12f", (3.01234567*i)+(3.54*j));
            }
			if(i%10 == 6){
                fprintf(stdout, "%3i", j);
                fprintf(stdout, "%c ", colon);
                fprintf(stdout, "%12d", (3.01234567*i)+(3.54*j));
            }
			if(i%10 == 7){
                fprintf(stdout, "%3i", j);
                fprintf(stdout, "%c ", colon);
                fprintf(stdout, "%12.2d", (3.01234567*i)+(3.54*j));
            }
			if(i%10 == 8){
                fprintf(stdout, "%3i", j);
                fprintf(stdout, "%c ", colon);
                fprintf(stdout, "%12c", ('A'+(i%26)));
            }
			if(i%10 == 9){
                fprintf(stdout, "%3i", j);
                fprintf(stdout, "%c ", colon);
                fprintf(stdout, "%12c", &word[i%8]);
            }
			if(i%10 == 0){
                fprintf(stdout, "%3i", j);
                fprintf(stdout, "%c ", colon);
                fprintf(stdout, "%12i", word((i%6)+4));
            }
			fprintf(stdout,"\n");
		}
	}
}
