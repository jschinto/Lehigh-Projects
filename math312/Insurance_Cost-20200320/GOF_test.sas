/* UNIQUE FEATURE OF POISSON RANDOM VARIABLE */
/* MEAN IS EQUAL TO VARIANCES                */
/* SAMPLE MEAN IS CLOSE TO SAMPLE VARIANCE   */
DATA POISSON01(DROP=NR I);
  ARRAY X{30};
  DO NR=1 TO 300;
    DO I=1 TO 30;
       X{I}=RAND('POISSON',2);
    END;
    OUTPUT;
  END;
RUN;
DATA POISSON01;
  SET POISSON01;
  MX=MEAN(OF X1-X30);
  VX=VAR(OF X1-X30);
RUN;

PROC MEANS MEAN DATA=POISSON01;
  VAR MX VX;
RUN;

/********************************************************************/
/* Example 5.2: Palominos pp.98 */
Data horse;
  INPUT color $ count;
DATALINES;
dark 21
palomino 52
light 23
;
RUN;

PROC FREQ DATA=horse ORDER=data;
  TABLE color/TESTP=(0.25 0.5 0.25) EXPECTED;
  WEIGHT COUNT;
RUN;

/* Example 5.3 pp.104 */
DATA sales;
  n=5;
  DO no=0 TO n;
     INPUT count @@;
     OUTPUT;
  END;
DATALINES;
15 21 40 14 6 4      
;

DATA sales;
  SET sales;
  prob_bin=PDF("BINOMIAL",no,0.35,n);
  Exp_bin=100*prob_bin;
RUN;
PROC PRINT DATA=sales;
  ID no; 
  VAR count prob_bin exp_bin;
RUN;

PROC FORMAT;
  VALUE salefmt 4-high="4+";
RUN;
PROC MEANS SUM NWAY DATA=sales;
  CLASS no; 
  VAR prob_bin;
  FORMAT no salefmt.;
  OUTPUT OUT=expect_p SUM=_testp_;
RUN;

PROC FREQ DATA=sales;
  TABLE no/CHISQ(TESTP=expect_p);
  FORMAT no salefmt.;
  WEIGHT count;
RUN;
/********************************************************************/

/* Example 5.4 pp.105 */
DATA typeset;
  INPUT no freq @@;
  DO i=1 TO freq;
     OUTPUT;
  END;
DATALINES;
0 13 
1 24 
2 31 
3 18 
4 11 
5 2 
6 1      
;

PROC COUNTREG DATA=typeset PLOTS(counts(0 to 6))=predprob;
  MODEL no = /DIST=POISSON;
  OUTPUT OUT=exp_poi PROB=prob_poi;
RUN;

PROC FORMAT;
  VALUE errfmt 5-high="Over 4";
RUN;
PROC MEANS SUM NWAY DATA=exp_poi NOPRINT;
  CLASS no; 
  VAR prob_poi;
  FORMAT no errfmt.;
  OUTPUT OUT=avgprobs mean=avgprob;
RUN;

PROC MEANS SUM NWAY DATA=avgprobs;
  CLASS no; 
  VAR avgprob;
  format no errfmt.;
  OUTPUT OUT=expect_p SUM=_testp_;
RUN;

PROC PRINT DATA=expect_p;
  ID no;
  SUM _testp_;
RUN;

DATA new;
  _testp_=1-CDF("POISSON",4,-log(0.1353353));
RUN;

DATA expect_p;
  SET expect_p(KEEP=_TESTP_ OBS=5) new;
RUN;

PROC PRINT; 
  SUM _testp_;
RUN;

PROC FREQ DATA=typeset;
  TABLE no/CHISQ(TESTP=expect_p);
  FORMAT no errfmt.;
RUN;
/********************************************************************/

/* Example on pp.108 */
DATA SPEECH;
  DO GENDER='MALE','FEMALE';
     DO OUTCOME='STAMMER','LISP';
	    INPUT NO @@;
		OUTPUT;
	 END;
  END;
DATALINES;
32 28 18 22 
;
PROC FREQ DATA=SPEECH ORDER=DATA;
  TABLE GENDER*OUTCOME/NOROW NOCOL NOPERCENT CHISQ EXPECTED;
  WEIGHT NO;
RUN;

/* Example5.5 on pp.110 */
DATA POLITICS;
  DO PARTY='DEMOCRATS','REPUBLICANS','INDEPENDENTS';
     DO OUTCOME='1','2','3','4';
	    INPUT NO @@;
		OUTPUT;
	 END;
  END;
DATALINES;
42 26 19 13
55 21 14 10
38 30 22 10
;
PROC FREQ DATA=POLITICS ORDER=DATA;
  TABLE PARTY*OUTCOME/NOROW NOCOL NOPERCENT CHISQ EXPECTED;
  WEIGHT NO;
RUN;


/* Example5.6 on pp.112 */
DATA GAME;
  DO OUTCOME='WON','LOST';
     DO TYPE='HOME','AWAY';
	    INPUT NO @@;
		OUTPUT;
	 END;
  END;
DATALINES;
97 69
42 83
;
PROC FREQ DATA=GAME ORDER=DATA;
  TABLE OUTCOME*TYPE/NOROW NOCOL NOPERCENT CHISQ EXPECTED;
  WEIGHT NO;
RUN;

