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
DATA FLOW;
  INPUT X1 X2 X3 X4 X5 X6 X7 X8 X9 Q ;
  Y=LOG(Q);
DATALINES;
.03 .006 3.0 1 70 1.5 .25 1.75 2.0 46
.03 .006 3.0 1 70 1.5 .25 2.25 3.7 28
.03 .006 3.0 1 70 1.5 .25 4.00 4.2 54
.03 .021 3.0 1 80 1.0 .25 1.60 1.5 70
.03 .021 3.0 1 80 1.0 .25 3.10 4.0 47
.03 .021 3.0 1 80 1.0 .25 3.60 2.4 112
.13 .005 6.5 2 65 2.0 .35 1.25 .7 398
.13 .005 6.5 2 65 2.0 .35 2.30 3.5 98
.13 .005 6.5 2 65 2.0 .35 4.25 4.0 191
.13 .008 6.5 2 68 .5 .15 1.45 2.0 171
.13 .008 6.5 2 68 .5 .15 2.60 4.0 150
.13 .008 6.5 2 68 .5 .15 3.90 3.0 331
1.00 .023 15.0 10 60 1.0 .20 .75 1.0 772
1.00 .023 15.0 10 60 1.0 .20 1.75 1.5 1268
1.00 .023 15.0 10 60 1.0 .20 3.25 4.0 849
1.00 .023 15.0 10 65 2.0 .20 1.80 1.0 2294
1.00 .023 15.0 10 65 2.0 .20 3.10 2.0 1984
1.00 .023 15.0 10 65 2.0 .20 4.75 6.0 900
3.00 .039 7.0 15 67 .5 .50 1.75 2.0 2181
3.00 .039 7.0 15 67 .5 .50 3.25 4.0 2484
3.00 .039 7.0 15 67 .5 .50 5.00 6.5 2450
5.00 .109 6.0 15 62 1.5 .60 1.50 1.5 1794
5.00 .109 6.0 15 62 1.5 .60 2.75 3.0 2067
5.00 .109 6.0 15 62 1.5 .60 4.20 5.0 2586
7.00 .055 6.5 19 56 2.0 .50 1.80 2.0 2410
7.00 .055 6.5 19 56 2.0 .50 3.25 4.0 1808
7.00 .055 6.5 19 56 2.0 .50 5.25 6.0 3024
7.00 .063 6.5 19 56 1.0 .50 1.25 2.0 710
7.00 .063 6.5 19 56 1.0 .50 2.90 3.4 3181
7.00 .063 6.5 19 56 1.0 .50 4.76 5.0 4279
;

PROC SGSCATTER DATA = FLOW;
  MATRIX Q X1-X9
                / ellipse
                  diagonal = (histogram normal);
RUN;

/*Boxplots*/
PROC SGPLOT DATA=FLOW;
  VBOX Q;
RUN;
PROC SGPLOT DATA=FLOW;
  VBOX X1;
RUN;
PROC SGPLOT DATA=FLOW;
  VBOX X2;
RUN;
PROC SGPLOT DATA=FLOW;
  VBOX X3;
RUN;
PROC SGPLOT DATA=FLOW;
  VBOX X4;
RUN;
PROC SGPLOT DATA=FLOW;
  VBOX X5;
RUN;
PROC SGPLOT DATA=FLOW;
  VBOX X6;
RUN;
PROC SGPLOT DATA=FLOW;
  VBOX X7;
RUN;
PROC SGPLOT DATA=FLOW;
  VBOX X8;
RUN;
PROC SGPLOT DATA=FLOW;
  VBOX X9;
RUN;

/*Correlation Analysis*/
PROC CORR DATA=FLOW SPEARMAN FISHER(BIASADJ=NO);
  VAR Q X1-X9;
RUN;
QUIT;

/*Full Model Fit w/ model doagnostics*/
PROC REG DATA=FLOW;
  MODEL Q = X1-X9/DWPROB VIF COLLIN;
  OUTPUT OUT=SFM_FIT RSTUDENT=D;
RUN;
QUIT;

%NORMTEST(D,SFM_FIT)

/*Full Model Fit w/ model doagnostics*/
PROC REG DATA=FLOW;
  MODEL Y = X1-X9/DWPROB VIF COLLIN;
  OUTPUT OUT=SFM_FIT RSTUDENT=D;
RUN;
QUIT;

%NORMTEST(D,SFM_FIT)

/*TRY CENTERING FIRST*/
PROC STDIZE DATA=FLOW OUT=FLOW2 METHOD=MEAN;
  VAR X1-X9;
RUN;

PROC REG DATA=FLOW2 PLOTS=NONE;
  MODEL Y=X1-X9/COLLIN VIF;
RUN;
QUIT;

/*COLLINEAR STILL*/
/*TRY RIDGE TRACE NEXT TO SEE IF DELETION OF INSIGNIFICANT VARIABLE CAN HELP */
PROC REG DATA=FLOW OUTEST=EST_RIDGE RIDGE=0.01 TO 1 BY 0.005 OUTVIF;
  MODEL Y= X1-X9;
RUN;
QUIT;

/*TRY DELETING X3*/
PROC REG DATA=FLOW PLOTS=NONE;
  MODEL Y= X1-X5 X7 X8/VIF COLLIN;
RUN;
QUIT;

PROC GLMSELECT DATA=FLOW PLOTS=ALL;
   MODEL Y=X1-X9/SELECTION=GROUPLASSO(CHOOSE=SBC STOP=NONE) CVMETHOD=RANDOM(10);
RUN;
PROC GLMSELECT DATA=FLOW;
   MODEL Y=X1-X9/SELECTION=ELASTICNET(CHOOSE=SBC STOP=NONE) CVMETHOD=RANDOM(10);
RUN;

PROC REG DATA=FLOW OUTEST=EST_RIDGE RIDGE=0.4 OUTVIF;
  MODEL Y= X1-X9;
RUN;
QUIT;
PROC PRINT DATA=EST_RIDGE;
  WHERE _TYPE_='RIDGE';
RUN;
/*(2-A2) PC REGRESSION IF NO SELECTION OF FEATURES IS INTENDED*/
/*PCA TO DECIDE HOW MANY COMPONENTS*/
PROC PRINCOMP DATA=FLOW;
  VAR X1-X9;
RUN;
PROC REG DATA=FLOW OUTEST=EST_PCR PCOMIT=4;
  MODEL Y= X1-X9;
RUN;
QUIT;
PROC PRINT DATA=EST_PCR;
  WHERE _TYPE_='IPC';
RUN;

PROC REG DATA=FLOW;
  MODEL Y = X3 X4 X6-X9/DWPROB VIF COLLIN;
  OUTPUT OUT=SFM_FIT RSTUDENT=D;
RUN;
QUIT;
%NORMTEST(D,SFM_FIT)
