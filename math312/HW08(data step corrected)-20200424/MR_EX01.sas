
/*MACRO for test on Normality*/

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

DATA SUPERVISOR;
  INPUT Y X1 X2 X3 X4 X5 X6;
DATALINES; 
43 	51 	30 	39 	61 	92 	45 
63 	64 	51 	54 	63 	73 	47 
71 	70 	68 	69 	76 	86 	48 
61 	63 	45 	47 	54 	84 	35 
81 	78 	56 	66 	71 	83 	47 
43 	55 	49 	44 	54 	49 	34 
58 	67 	42 	56 	66 	68 	35 
71 	75 	50 	55 	70 	66 	41 
72 	82 	72 	67 	71 	83 	31 
67 	61 	45 	47 	62 	80 	41 
64 	53 	53 	58 	58 	67 	34 
67 	60 	47 	39 	59 	74 	41 
69 	62 	57 	42 	55 	63 	25 
68 	83 	83 	45 	59 	77 	35 
77 	77 	54 	72 	79 	77 	46 
81 	90 	50 	72 	60 	54 	36 
74 	85 	64 	69 	79 	79 	63 
65 	60 	65 	75 	55 	80 	60 
65 	70 	46 	57 	75 	85 	46 
50 	58 	68 	54 	64 	78 	52 
50 	40 	33 	34 	43 	64 	33 
64 	61 	52 	62 	66 	80 	41 
53 	66 	52 	50 	63 	80 	37 
40 	37 	42 	58 	50 	57 	49 
63 	54 	42 	48 	66 	75 	33 
66 	77 	66 	63 	88 	76 	72 
78 	75 	58 	74 	80 	78 	49 
48 	57 	44 	45 	51 	83 	38 
85 	85 	71 	71 	77 	74 	55 
82 	82 	39 	59 	64 	78 	39 
;

ODS RTF FILE='C:\work\Teaching\Math312SP2020\play.rtf'; 

PROC SGSCATTER DATA = SUPERVISOR;
  MATRIX Y X1-X6
                / ellipse
                  diagonal = (histogram normal);
RUN;

/*Scatter matrix plus Correlation analysis*/
PROC CORR DATA=SUPERVISOR SPEARMAN FISHER(BIASADJ=NO) PLOTS=MATRIX(HISTOGRAM NVAR=10);
  VAR Y X1-X6;
RUN;

/*Boxplots*/
PROC SGPLOT DATA=SUPERVISOR;
  VBOX X1;
RUN;
PROC SGPLOT DATA=SUPERVISOR;
  VBOX X2;
RUN;
PROC SGPLOT DATA=SUPERVISOR;
  VBOX X3;
RUN;
PROC SGPLOT DATA=SUPERVISOR;
  VBOX X4;
RUN;
PROC SGPLOT DATA=SUPERVISOR;
  VBOX X5;
RUN;
PROC SGPLOT DATA=SUPERVISOR;
  VBOX X6;
RUN;

/*Full Model Fit w/ model doagnostics*/
PROC REG DATA=SUPERVISOR;
  MODEL Y = X1-X6/DWPROB VIF COLLIN;
  OUTPUT OUT=SFM_FIT RSTUDENT=D;
RUN;
QUIT;

%NORMTEST(D,SFM_FIT)

/*Model Selection*/
PROC REG DATA=SUPERVISOR PLOTS(LABEL)=CRITERIA;
  MODEL Y = X1-X6/SELECTION=ADJRSQ CP AIC BIC SBC;
RUN;
QUIT;

PROC REG DATA=SUPERVISOR PLOTS(LABEL)=CRITERIA;
  MODEL Y = X1-X6/SELECTION=FORWARD;
RUN;
QUIT;

PROC REG DATA=SUPERVISOR PLOTS(LABEL)=CRITERIA;
  MODEL Y = X1-X6/SELECTION=BACKWARD;
RUN;
QUIT;

PROC REG DATA=SUPERVISOR PLOTS(LABEL)=CRITERIA;
  MODEL Y = X1-X6/SELECTION=STEPWISE;
RUN;
QUIT;

PROC GLMSELECT DATA=SUPERVISOR PLOTS=ALL;
   MODEL Y = X1-X6/SELECTION=LASSO(CHOOSE=CV STOP=NONE) CVMETHOD=RANDOM(10);
RUN;

PROC GLMSELECT DATA=SUPERVISOR PLOTS=ALL;
   MODEL Y=X1-X6/SELECTION=ELASTICNET(CHOOSE=CV STOP=NONE) CVMETHOD=RANDOM(10);
RUN;

/*ASSUME (X1 X3 X6) IS SELECTED*/

PROC REG DATA=SUPERVISOR PLOTS(LABEL)=(COOKSD DIAGNOSTICS RESIDUALS(SMOOTH));
  MODEL Y = X1 X3 X6/DWPROB INFLUENCE;
  OUTPUT OUT=SRM_FIT RSTUDENT=D;
RUN;
QUIT;

%NORMTEST(D,SRM_FIT)


/*Standardize X1 X3 X6 to compare the impact*/
PROC STDIZE DATA=SUPERVISOR OUT=STDSUPERVISOR;
  VAR X1 X3 X6;
RUN;
 
PROC REG DATA=STDSUPERVISOR OUTEST=SRM_EST PLOTS=NONE;
  MODEL Y = X1 X3 X6;
RUN;
QUIT;

ODS RTF CLOSE;
