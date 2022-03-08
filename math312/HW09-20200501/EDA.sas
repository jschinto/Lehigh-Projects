/***************************************************************************************/
/*Exploratory Data Analysis EDA*/
/*EDA is an approach/philosophy for data analysis that employs a variety of techniques */
/*(mostly graphical) to                                                                */
/*(1) maximize insight into a data set;                                                */
/*(2) uncover underlying structure;                                                    */
/*(3) extract important variables;                                                     */
/*(4) detect outliers and anomalies;                                                   */
/*(5) test underlying assumptions;                                                     */
/*(6) develop parsimonious models; and                                                 */
/*(7) determine optimal factor settings.                                               */
/*From https://www.itl.nist.gov/div898/handbook/eda/section1/eda11.htm                 */
/*                                                                                     */
/*In short, to have a better understanding of the data. You might consider             */
/*                                                                                     */
/* Univariate attribute                                                                */
/* (1) continuous: summary statistics (5-number summary, mean and std),                */
/*    boxplot and histogram.                                                           */
/* (2) categorical: frequency table.                                                   */
/*                                                                                     */
/* Bivariate attributes                                                                */
/* (1) both continuous: correlation analysis (both Pearson and Spearman corr. coeff.). */
/*     scatter plot.                                                                   */
/* (2) both categorical: contingency table.                                            */
/* (3) one continuous (Y) and one categorical (X): most likely y over levels of X,     */
/*    side by side boxplots, side by side histograms.                                  */
/*                                                                                     */
/* Multivariate                                                                        */
/* (1) all continuous: corrlation analysis, scatter matrix, 3-D scatter plots, PCA     */
/* (2) all categorical: k-way contigency table.                                        */    
/***************************************************************************************/

DATA CEMENT;
  INPUT grp1 $ grp2 $ y x1 x2 x3 x4;
  LABEL grp1='Group-1st layer'
        grp2='Group-2nd layer'
        y = 'calories per gram of cement'
        x1= 'calcium aluminate'
        x2= 'tricalcium silicate'
        x3= 'tricalcium aluminoferrite'
        x4= 'dicalcium silicate';
DATALINES;
1 1 78.5 7 26 6 60
2 1 74.3 1 29 15 52
3 1 104.3 11 56 8 20
1 1 87.6 11 31 8 47
2 1 95.9 7 52 6 33
3 1 109.2 11 55 9 22
1 2 102.7 3 71 17 6
2 2 72.5 1 31 22 44
3 2 93.1 2 54 18 22
1 2 115.9 21 47 4 26
2 2 83.8 1 40 23 34
3 2 113.3 11 66 9 12
1 2 109.4 10 68 8 12
;
RUN;

/***************************************************************************************/
/* Univariate attribute                                                                */
/* (1) continuous: summary statistics (5-number summary, mean and std),                */
/*    boxplot and histogram.                                                           */
PROC UNIVARIATE DATA=CEMENT;
  VAR Y X1 X2 X3 X4;
  HISTOGRAM;
RUN; 

PROC SGPLOT DATA=CEMENT;
  VBOX X1;
RUN;

/* (2) categorical: frequency table.                                                   */
PROC FREQ DATA=CEMENT;
  TABLES GRP1 GRP2;
RUN;

/***************************************************************************************/
/* Bivariate attributes                                                                */
/* (1) both continuous: correlation analysis (both Pearson and Spearman corr. coeff.). */
/*     scatter plot.                                                                   */
PROC CORR DATA=CEMENT SPEARMAN FISHER(BIASADJ=NO) PLOTS=MATRIX(HISTOGRAM);
  VAR Y X1 X2 X3 X4;
RUN; 
/* (2) both categorical: contingency table.                                            */
PROC FREQ DATA=CEMENT;
  TABLES GRP1*GRP2;
RUN;
/* (3) one continuous (Y) and one categorical (X): most likely y over levels of X,     */
/*    side by side boxplots, side by side histograms.                                  */
PROC SGPLOT DATA=CEMENT;
  VBOX Y/GROUP=GRP;
RUN;

/***************************************************************************************/
/* Multivariate                                                                        */
/* (1) all continuous: corrlation analysis, scatter matrix, 3-D scatter plots, PCA     */
PROC CORR DATA=CEMENT SPEARMAN FISHER(BIASADJ=NO) PLOTS=MATRIX(HISTOGRAM);
  VAR ;
RUN; 
PROC G3D DATA=CEMENT;
   SCATTER X1 * X2 = Y;
RUN;
QUIT;
PROC SGSCATTER DATA=CEMENT;
  MATRIX Y X1 X2 X3 X4/ GROUP=GRP1;
RUN;
PROC SGSCATTER DATA=CEMENT;
  MATRIX Y X1 X2 X3 X4/ GROUP=GRP2;
RUN;
PROC PRINCOMP DATA=CEMENT;
  VAR X1-X4;
RUN;

/* (2) all categorical: k-way contigency table.                                        */    
/*Similar to 2-way contigency table: FREQ procedure with gp1*gp2*...*gpk in TABLES statement*/
