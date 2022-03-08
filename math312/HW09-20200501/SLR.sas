/*EXAMPLE pp.216*/

/*Creation of dataset*/
DATA ASSEMBLY;
  INPUT X Y;
DATALINES;
1 5
2 4
3 6
4 8
5 7
;
/*Generating Scatter Plot -- Option-1: general approaach*/
PROC SGPLOT DATA=ASSEMBLY;
  SCATTER X=X Y=Y;
RUN;

/*Generating Scatter Plot -- Option-2: along with correlation analysis*/
PROC CORR DATA=ASSEMBLY PLOTS=MATRIX;
  VAR X Y;
RUN;

/*Fitting SLR*/
PROC REG DATA=ASSEMBLY;
  MODEL Y=X;
RUN;
QUIT;


/*Example pp.239 used in the annotated example*/
DATA CALCULATOR;
  INPUT X Y;
DATALINES;
23	19
18	18
29	24
22	23
33	31
20	22
17	16
25	23
27	24
30	26
25	24
27	28
;

ODS RTF FILE="PLAY01.RTF";
PROC CORR DATA=CALCULATOR SPEARMAN FISHER(BIASADJ=NO) PLOTS=MATRIX;
  VAR X Y;
RUN;

PROC REG DATA=CALCULATOR PLOTS(LABEL)=ALL;
  MODEL Y=X/INFLUENCE DWPROB;
  OUTPUT OUT=SLRFIT R=R;
RUN;
QUIT;


%MACRO NORMTEST(VAR,DATA);
/*********************************************************************************/
/* Macro NORMTEST is revised from the code in D'Agostino's paper.                */
/* "A Suggestion for Using Powerful and Informative Tests of Normality"          */
/* Author(s): Ralph B. D'Agostino, Albert Belanger, and Ralph B. D'Agostino Jr.  */
/* Source: The American Statistician, Vol. 44, No. 4 (Nov., 1990), pp. 316-321   */

/* It provides five hypothesis tests                                             */
/* (1) Shapiro-Wilk test                                                         */
/* (2) Kolmogorov-Smirnov test                                                   */
/* (3) Cramer-von Mises test                                                     */
/* (4) Anderson-Darling                                                          */
/* (5) D'Agostino's K^2                                                          */
/* For details about the first four tests, users are referred to SAS online doc  */
/* under UNIVARIATE procedure. As for D'Agostino's test, please refer to the art.*/
/* mentioned above.                                                              */
/* Revised by Ping-Shi Wu Dec. 2015 @ Lehigh University                          */
/*********************************************************************************/
  ODS NOPROCTITLE;
  ODS GRAPHICS /BORDER=OFF; 
  ODS SELECT Moments Histogram QQPlot CDFPlot;
  TITLE "NORMAL-TEST"; 
  PROC UNIVARIATE DATA=&DATA NORMAL;
    VAR &VAR;
	HISTOGRAM &VAR/NORMAL(MU=EST SIGMA=EST) KERNEL;
    QQPLOT &VAR/NORMAL(MU=EST SIGMA=EST);
    CDFPLOT &VAR/NORMAL(MU=EST SIGMA=EST);
    OUTPUT OUT=XXSTAT N=N MEAN=XBAR STD=S SKEWNESS=G1 KURTOSIS=G2;
  RUN; 
  ODS SELECT TestsForNormality;
  PROC UNIVARIATE DATA=&DATA NORMAL;
    VAR &VAR;
  RUN; 
  TITLE;
  OPTIONS LS=80;
  DATA _NULL_;
    SET XXSTAT;
    SQRTB1=(N-2)/SQRT(N*(N-1))*G1;
    Y=SQRTB1*SQRT((N+1)*(N+3)/(6*(N-2)));
    BETA2=3*(N*N+27*N-70)*(N+1)*(N+3)/((N-2)*(N+5)*(N+7)*(N+9));
    W=SQRT(-1+SQRT(2*(BETA2-1)));
    DELTA=1/SQRT(LOG(W));
    ALPHA=SQRT(2/(W*W-1));
	Z_B1=DELTA*LOG(Y/ALPHA+SQRT((Y/ALPHA)**2+1));
    B2=3*(N-1)/(N+1)+(N-2)*(N-3)/((N+1)*(N-1))*G2;
    MEANB2=3*(N-1)/(N+1);
    VARB2= 24*N*(N-2)*(N-3)/((N+1)*(N+1)*(N+3)*(N+5));
    X=(B2-MEANB2)/SQRT(VARB2);
    MOMENT=6*(N*N-5*N+2)/((N+7)*(N+9))*SQRT(6*(N+3)*(N+5)/(N*(N-2)*(N-3)));
    A=6+8/MOMENT*(2/MOMENT+SQRT(1+4/(MOMENT**2)));
    Z_B2=(1-2/(9*A)-((1-2/A)/(1+ X*SQRT(2/(A-4))))**(1/3))/SQRT(2/(9*A));
    PRZB1=2*(1-PROBNORM(ABS(Z_B1)));
    PRZB2=2*(1-PROBNORM(ABS(Z_B2)));
    CHITEST=Z_B1*Z_B1 + Z_B2*Z_B2;
    PRCHI=1-PROBCHI(CHITEST,2);
    FILE PRINT;
    PUT @22 "D'AGOSTINO TEST OF NORMALITY FOR VARIABLE &VAR, "
    N = /@20 G1=8.5 @33 SQRTB1 =8.5 @50 "Z=" Z_B1 8.5 @65 "P=" PRZB1 6.4
        /@20 G2=8.5 @33 B2=8.5 @50 "Z=" Z_B2 8.5 @65 "P=" PRZB2 6.4
        /@20 "K**2=CHISQ(2 DF)=" CHITEST 8.5 @65 "P=" PRCHI 6.4;
  RUN;
  TITLE;
%MEND NORMTEST;

%NORMTEST(R,SLRFIT)  
ODS RTF CLOSE;

/*Example on pp. 244*/
DATA VACCINES;
  INPUT X Y;
DATALINES;
10	22
19	26
17	22
9	18
5	20
4	17
8	15
16	30
;
PROC CORR DATA=VACCINES SPEARMAN FISHER(BIASADJ=NO) PLOTS=MATRIX;
  VAR X Y;
RUN;

/*Ex 9.5.1 on pp. 253*/
DATA POTTERY;
  INPUT X Y;
DATALINES;
10.5	10.7
15.3	15.6
12.4	12.2
12.9	12.7
14.4	14.5
11.6	11.3
12.9	13
13.6	14
10.8	10.6
14.6	14.5
;
PROC CORR DATA=POTTERY SPEARMAN FISHER(BIASADJ=NO) PLOTS=MATRIX;
  VAR X Y;
RUN;

PROC REG DATA=POTTERY;
  MODEL Y=X;
RUN;
QUIT;
