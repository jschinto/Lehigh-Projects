/* Multiple Regression Example02           */
/* Model selection under multicollinearity */


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


DATA CEMENT;
  INPUT y x1 x2 x3 x4;
  LABEL y = 'calories per gram of cement'
        x1= 'calcium aluminate'
        x2= 'tricalcium silicate'
        x3= 'tricalcium aluminoferrite'
		x4= 'dicalcium silicate';
DATALINES;
78.5	7	26	6	60
74.3	1	29	15	52
104.3	11	56	8	20
87.6	11	31	8	47
95.9	7	52	6	33
109.2	11	55	9	22
102.7	3	71	17	6
72.5	1	31	22	44
93.1	2	54	18	22
115.9	21	47	4	26
83.8	1	40	23	34
113.3	11	66	9	12
109.4	10	68	8	12
;
RUN;
TITLE;

ODS RTF FILE='PLAY.RTF';
/*EDA*/
PROC SGSCATTER DATA = CEMENT;
  MATRIX Y X1-X4
                / ELLIPSE
                  DIAGONAL = (histogram normal);
RUN;

/*Correlation Analysis*/
PROC CORR DATA=CEMENT;
  VAR Y X1-X4;
RUN;
QUIT;

/*Full Model*/
PROC REG DATA=CEMENT;
  MODEL Y=X1-X4/DWPROB COLLIN VIF;
  OUTPUT OUT=CFM_FIT RSTUDENT=D;
RUN;
QUIT;

%NORMTEST(D,CFM_FIT)

/*NO VIOLATION AGAINST ASSUMPTION BUT MULTICOLLINEARITY DISCOVERED*/

/*TRY CENTERING FIRST*/
PROC STDIZE DATA=CEMENT OUT=CEMENT02 METHOD=MEAN;
  VAR X1-X4;
RUN;

PROC REG DATA=CEMENT02 PLOTS=NONE;
  MODEL Y=X1-X4/COLLIN VIF;
RUN;
QUIT;

/*COLLINEAR STILL*/
/*TRY RIDGE TRACE NEXT TO SEE IF DELETION OF INSIGNIFICANT VARIABLE CAN HELP */
PROC REG DATA=CEMENT OUTEST=EST_RIDGE RIDGE=0.01 TO 1 BY 0.005 OUTVIF;
  MODEL Y= X1-X4;
RUN;
QUIT;

/*TRY DELETING X3*/
PROC REG DATA=CEMENT PLOTS=NONE;
  MODEL Y= X1 X2 X4/VIF COLLIN;
RUN;
QUIT;

/*DELETION X3 ALLEVIATES THE COLLINEARITY A LOT, BUT NOT COMPLETELY REMOVE */
/*(1) PASS SINCE IT HELPS TO ALLEVIATE THE COLLINEARITY*/
/*(2) NO PASS; PROCEED WITH */
/*(2-A1) RIDGE REGRESSION IF NO SELECTION OF FEATURES IS INTENDED*/
/*(2-A1) PC REGRESSION IF NO SELECTION OF FEATURES IS INTENDED*/
/*(2-B) (GROUP) LASSO IF SELECTION OF FEATURES IS INTENDED*/

/* 1. ALL REGRESSION MODELS */
PROC REG DATA=CEMENT PLOTS(ONLY)=CRITERIONPANEL(UNPACK LABELVARS);
/*PROC REG DATA=CEMENT PLOTS(ONLY)=CRITERIONPANEL;*/
  MODEL Y=X1 X2 X4/SELECTION=ADJRSQ AIC BIC SBC CP;
  PLOT CP.*NP./VAXIS=0 TO 250 BY 50 HAXIS=0 TO 4 BY 1 CHOCKING=RED NOMODEL NOSTAT;
RUN;
QUIT;

PROC REG DATA=CEMENT PLOTS(ONLY)=CRITERIONPANEL;
  MODEL Y=X1 X2 X4/SELECTION=F DETAILS;
RUN;
QUIT;

PROC REG DATA=CEMENT PLOTS(ONLY)=CRITERIONPANEL;
  MODEL Y=X1-X4/SELECTION=B DETAILS;
RUN;
QUIT;

PROC REG DATA=CEMENT PLOTS(ONLY)=CRITERIONPANEL;
  MODEL Y=X1 X2 X4/SELECTION=STEPWISE DETAILS;
RUN;
QUIT;

/*X1 X2 IS THE MAJOR WINNER*/
PROC REG DATA=CEMENT;
  MODEL Y=X1 X2/DWPROB COLLIN VIF;
  OUTPUT OUT=FC_FIT RSTUDENT=D;
RUN;
QUIT;

%NORMTEST(D,FC_FIT)

PROC STDIZE DATA=CEMENT OUT=STDCEMENT;
  VAR X1 X2;
RUN;
 
PROC REG DATA=STDCEMENT OUTEST=SRM_EST PLOTS=NONE;
  MODEL Y = X1 X2;
RUN;
QUIT;

/*(2-A1) RIDGE REGRESSION IF NO SELECTION OF FEATURES IS INTENDED*/
PROC REG DATA=CEMENT OUTEST=EST_RIDGE RIDGE=1 OUTVIF;
  MODEL Y= X1-X4;
RUN;
QUIT;
PROC PRINT DATA=EST_RIDGE;
  WHERE _TYPE_='RIDGE';
RUN;
/*(2-A2) PC REGRESSION IF NO SELECTION OF FEATURES IS INTENDED*/
/*PCA TO DECIDE HOW MANY COMPONENTS*/
PROC PRINCOMP DATA=CEMENT;
  VAR X1-X4;
RUN;
PROC REG DATA=CEMENT OUTEST=EST_PCR PCOMIT=2;
  MODEL Y= X1-X4;
RUN;
QUIT;
PROC PRINT DATA=EST_PCR;
  WHERE _TYPE_='IPC';
RUN;
/*(2-B) (GROUP) LASSO IF SELECTION OF FEATURES IS INTENDED*/
PROC GLMSELECT DATA=CEMENT PLOTS=ALL;
   MODEL Y=X1-X4/SELECTION=GROUPLASSO(CHOOSE=SBC STOP=NONE) CVMETHOD=RANDOM(10);
RUN;
PROC GLMSELECT DATA=CEMENT;
   MODEL Y=X1-X4/SELECTION=ELASTICNET(CHOOSE=SBC STOP=NONE) CVMETHOD=RANDOM(10);
RUN;
ODS RTF CLOSE;
