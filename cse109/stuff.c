    //Group 1, Onur Gencer Ates, Siraphob Limprapaipong, Jake Schinto

    #include<stdio.h>
    #include<stdlib.h>
    #include<string.h>
    #include<errno.h>

    int main(int argc, char **argv) 
    {
            int x;
            int *y;
            int counter = 0;
            int z,size,capacity;
            while(1){
                scanf("%d", &x);
                if (x == 1)
                {
                    break;
                }

                else if (x == 2)
                {
                    y = (int*)malloc(3*sizeof(int));
                    size = 0;
                    capacity = 3;
                    while(fscanf(stdin, "%d" , &z) != EOF)
                    { 
                        if(z == 0)
                        {
                            int sum = 0;
                            for(size_t i = 0; i<size;i++)
                            {
                                sum += y[i];
                                fprintf(stdout,"%d %d",y[i],sum);
                            }
                            break;
                        }
                        y[size] = z;
                        size++;
                        if (size == capacity)
                        {
                            int  *temp = (int*)malloc((capacity+1)*sizeof(int));
                            for(size_t i=0;i<size;i++)
                            temp[i] = y[i];
                            capacity++;
                            free(y);
                            counter++;
                            y = temp;
                        }
                    }
                }

                else if (x == 3)
                {
                    y = (int*)malloc(3*sizeof(int));
                    size = 0;
                    capacity = 3;
                    while(fscanf(stdin, "%d" , &z) != EOF)
                    { 
                        if(z == 0)
                        {
                            int sum = 0;
                            for(size_t i = 0; i<size;i++)
                            {
                                sum += y[i];
                                fprintf(stdout,"%d %d",y[i],sum);
                            }
                            break;
                        }
                        y[size] = z;
                        size++;
                        if (size == capacity)
                        {
                            int  *temp = (int*)malloc((capacity*2)*sizeof(int));
                            for(size_t i=0;i<size;i++)
                            temp[i] = y[i];
                            capacity++;
                            free(y);
                            counter++;
                            y = temp;
                        }
                    }
                }
    /*
                else if (x == 4)
                {

                }
    */
                else if (x == 5)
                {
                    char *chpointer;
                    int size5 = 10;
                    fscanf(stdin,"%ms",&chpointer);
                    if (strcmp(chpointer, "apple") != 0)
                    {
                        char *temp = NULL;
                        while(strcmp(temp, "apple")!= 0)
                        {
                            fscanf(stdin,"%ms",&temp);
                            if (strcmp(temp, "apple")!= 0) 
                            {
                                free(chpointer);
                                counter++;
                                chpointer = temp;
                            }
                            else
                            {
                                fprintf(stdout, "%s\n", chpointer);
                                break;
                            }
                        }
                    }
                }

                else if (x == 6)
                {
                    int size6 = 3;
                    char *chpointer = (char*)malloc(size6*sizeof(char));
                    char *temp;
                    fscanf(stdin,"%ms",&temp);
                    if (strcmp(temp, "apple") != 0)
                    {
                        while(strcmp(temp, "apple")!= 0)
                        {
                            fscanf(stdin,"%ms",&temp);
                            if (strcmp(temp, "apple")!= 0) 
                            {
                                if (strlen(temp)>size6 - strlen(chpointer))
                                {
                                    int  *temp1 = (int*)malloc((size6*2)*sizeof(int));
                                    for(size_t i=0;i<strlen(chpointer);i++)
                                    temp1[i] = chpointer[i];
                                    for(size_t i=strlen(chpointer);i<strlen(temp)+strlen(chpointer);i++)
                                    temp1[i] = temp[i+strlen(chpointer)];
                                    counter++;
                                    y = temp;
                                }
                                free(chpointer);
                                counter++;
                                chpointer = temp;
                            }
                            else
                            {
                                fprintf(stdout, "%s\n", chpointer);
                                break;
                            }
                        }
                    }
                }

                /*else if (x == 7)
                {

                }

                else{

                }*/

            }
            fprintf(stdout, "%d", counter);
            return 0;


    }
