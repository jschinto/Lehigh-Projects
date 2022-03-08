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
/* You need to change the path in "" according to where you saved CarPrice_Assignment.csv.*/
/* Similarly, you also need to change the DATAFILE="" as well.                            */
PROC IMPORT DATAFILE="H:\Math312\Insurance_Cost-20200320\insurance.csv" 
            OUT=INSURANCE
			DBMS=CSV
            REPLACE; 
RUN;

/* Data is in the following collumns:
AGE SEX BMI CHILDREN SMOKER REGION CHARGES

AGE: Num
SEX: String (male, female)
BMI: Num
CHILDREN: Num
SMOKER: String (yes, no)
REGION: String (northwest, northeast, southwest, southeast)
CHARGES: Num
*/

DATA INSURANCE2;
    SET INSURANCE;
    IF SEX='male' THEN SEX=0;
	ELSE SEX=1;
	TEMP = input(SEX, best12.);
	DROP SEX;
	RENAME TEMP=sex;
	IF SMOKER='no' THEN SMOKER=0;
	ELSE SMOKER=1;
	TEMP2 = input(SMOKER, best12.);
	DROP SMOKER;
	RENAME TEMP2=smoker;
	northwest=0;
	northeast=0;
	southwest=0;
	southeast=0;
	IF REGION='northwest' THEN northwest=1;
	IF REGION='northeast' THEN northeast=1;
	IF REGION='southwest' THEN southwest=1;
	IF REGION='southeast' THEN southeast=1;
	DROP REGION;
RUN;

PROC SGSCATTER DATA = INSURANCE2;
  MATRIX CHARGES AGE BMI CHILDREN SEX SMOKER NORTHWEST NORTHEAST SOUTHWEST SOUTHEAST
                / ellipse
                  diagonal = (histogram normal);
RUN;

/*Boxplots*/
PROC SGPLOT DATA=INSURANCE;
  VBOX CHARGES;
RUN;
PROC SGPLOT DATA=INSURANCE;
  VBOX AGE;
RUN;
PROC SGPLOT DATA=INSURANCE;
  VBOX BMI;
RUN;
PROC SGPLOT DATA=INSURANCE;
  VBOX CHILDREN;
RUN;

/*Pie Charts*/
PROC TEMPLATE;
   DEFINE STATGRAPH pie;
      BEGINGRAPH;
         LAYOUT REGION;
            PIECHART CATEGORY = SEX /
            DATALABELLOCATION = OUTSIDE
            CATEGORYDIRECTION = CLOCKWISE
            START = 180 NAME = 'pie';
            DISCRETELEGEND 'pie' /
            TITLE = 'Gender';
         ENDLAYOUT;
      ENDGRAPH;
   END;
RUN;
PROC TEMPLATE;
   DEFINE STATGRAPH pie2;
      BEGINGRAPH;
         LAYOUT REGION;
            PIECHART CATEGORY = SMOKER /
            DATALABELLOCATION = OUTSIDE
            CATEGORYDIRECTION = CLOCKWISE
            START = 180 NAME = 'pie';
            DISCRETELEGEND 'pie' /
            TITLE = 'Smoker';
         ENDLAYOUT;
      ENDGRAPH;
   END;
RUN;
PROC TEMPLATE;
   DEFINE STATGRAPH pie3;
      BEGINGRAPH;
         LAYOUT REGION;
            PIECHART CATEGORY = REGION /
            DATALABELLOCATION = OUTSIDE
            CATEGORYDIRECTION = CLOCKWISE
            START = 180 NAME = 'pie';
            DISCRETELEGEND 'pie' /
            TITLE = 'Region';
         ENDLAYOUT;
      ENDGRAPH;
   END;
RUN;
PROC SGRENDER DATA = INSURANCE
            TEMPLATE = pie;
RUN;
PROC SGRENDER DATA = INSURANCE
            TEMPLATE = pie2;
RUN;
PROC SGRENDER DATA = INSURANCE
            TEMPLATE = pie3;
RUN;

/*Boxplot split by sex, smoker, and region*/
PROC SGPLOT DATA=INSURANCE;
  VBOX CHARGES/GROUP=SEX;
RUN;
PROC SGPLOT DATA=INSURANCE;
  VBOX CHARGES/GROUP=SMOKER;
RUN;
PROC SGPLOT DATA=INSURANCE;
  VBOX CHARGES/GROUP=REGION;
RUN;

DATA INSURANCE3;
	SET INSURANCE2;
	/*AGE=AGE**2;*/
	/*IF BMI>=25 && BMI<30 THEN BMI=1;
	ELSE IF BMI>=30 && BMI<35 THEN BMI=2;
	ELSE IF BMI>=35 && BMI<40 THEN BMI=3;
	ELSE IF BMI>=40 THEN BMI=4;
	ELSE BMI=0;*/
	/*IF CHILDREN>0 THEN CHILDREN=1;*/
	CHARGES = CHARGES;
	LOGCHARGE=LOG(CHARGES);
	SQRTCHARGE=SQRT(CHARGES);
RUN;
/*Correlation Analysis*/
PROC CORR DATA=INSURANCE3;
RUN;

/*Full Model Fit w/ model doagnostics*/
PROC REG DATA=INSURANCE3;
  MODEL CHARGES = AGE BMI CHILDREN SEX SMOKER SOUTHEAST /DWPROB VIF COLLIN;
  OUTPUT OUT=SFM_FIT RSTUDENT=D;
RUN;
QUIT;

%NORMTEST(D,SFM_FIT)

/*Full Model Fit w/ model doagnostics*/
PROC REG DATA=INSURANCE3;
  MODEL LOGCHARGE = AGE BMI CHILDREN SEX SMOKER SOUTHEAST /DWPROB VIF COLLIN;
  OUTPUT OUT=SFM_FIT RSTUDENT=D;
RUN;
QUIT;

%NORMTEST(D,SFM_FIT)
PROC REG DATA=INSURANCE3;
  MODEL SQRTCHARGE = AGE BMI CHILDREN SEX SMOKER SOUTHEAST /DWPROB VIF COLLIN;
  OUTPUT OUT=SFM_FIT RSTUDENT=D;
RUN;
QUIT;

%NORMTEST(D,SFM_FIT)

/*Model Selection*/
PROC REG DATA=INSURANCE3 PLOTS(LABEL)=CRITERIA;
	MODEL LOGCHARGE = AGE BMI CHILDREN SEX SMOKER SOUTHEAST/SELECTION=ADJRSQ CP AIC BIC SBC;
RUN;
QUIT;

PROC REG DATA=INSURANCE3 PLOTS(LABEL)=CRITERIA;
	MODEL LOGCHARGE = AGE BMI CHILDREN SEX SMOKER SOUTHEAST/SELECTION=FORWARD;
RUN;
QUIT;

PROC REG DATA=INSURANCE3 PLOTS(LABEL)=CRITERIA;
	MODEL LOGCHARGE = AGE BMI CHILDREN SEX SMOKER SOUTHEAST/SELECTION=BACKWARD;
RUN;
QUIT;

PROC REG DATA=INSURANCE3 PLOTS(LABEL)=CRITERIA;
	MODEL LOGCHARGE = AGE BMI CHILDREN SEX SMOKER SOUTHEAST/SELECTION=STEPWISE;
RUN;
QUIT;

PROC GLMSELECT DATA=INSURANCE3 PLOTS=ALL;
	MODEL LOGCHARGE = AGE BMI CHILDREN SEX SMOKER SOUTHEAST/SELECTION=LASSO(CHOOSE=CV STOP=NONE) CVMETHOD=RANDOM(10);
RUN;

PROC GLMSELECT DATA=INSURANCE3 PLOTS=ALL;
	MODEL LOGCHARGE = AGE BMI CHILDREN SEX SMOKER SOUTHEAST/SELECTION=ELASTICNET(CHOOSE=CV STOP=NONE) CVMETHOD=RANDOM(10);
RUN;

PROC REG DATA=INSURANCE3 PLOTS(LABEL)=(COOKSD DIAGNOSTICS RESIDUALS(SMOOTH));
	MODEL LOGCHARGE = AGE BMI CHILDREN SEX SMOKER SOUTHEAST/DWPROB INFLUENCE;
	OUTPUT OUT=SRM_FIT RSTUDENT=D;
RUN;
QUIT;

%NORMTEST(D,SRM_FIT)
