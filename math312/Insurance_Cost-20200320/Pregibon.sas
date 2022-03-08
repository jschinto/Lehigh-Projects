

%MACRO PREGIBON2(DATA = , Y = , X = , FACTOR= );
/***********************************************************/
/* SAS MACRO PERFORMING PREGIBON TEST FOR GOODNESS OF LINK */
/* ======================================================= */
/* INPUT PAREMETERS:                                       */
/*  DATA : NAME OF SAS DATA TABLE                          */
/*  Y    : THE DEPENDENT VARIABLE WITH EVENT SPECIFICATION */
/*  X    : MODEL PREDICTORS                                */
/*  FACTOR: LIST OF CATEGORICAL VARIABLES                  */
/* ======================================================= */
/* AUTHOR: WENSUI.LIU@53.COM                               */
/* EDITED BY PSW TO ACCOMMODATE CATEGORICAL PREDICTORS     */
/*                              AND NON 0-1 OUTCOME        */
/***********************************************************/
OPTIONS MPRINT MLOGIC;
 
%LET links = LOGIT PROBIT CLOGLOG;
%LET loop = 1;
%LET event=%SCAN(&Y,2,'"');
 
PROC SQL NOPRINT;
  SELECT COUNT(*) - 3 INTO :df FROM &data;
QUIT; 
 
%DO %WHILE (%SCAN(&links, &loop) NE %STR());
 
  %LET link = %SCAN(&links, &loop);
 
  PROC LOGISTIC DATA = &data NOPRINT;
  %IF %SCAN(&FACTOR,1) NE %STR() %THEN %DO;
    CLASS &FACTOR;
  %END;
    MODEL &y = &x /LINK = &link;
    SCORE DATA = &data OUT = _out1;
  RUN;

  DATA _out2;
    SET _out1(RENAME = (p_&event = p&event));
    p&event.2 = p&event * p&event;
  RUN;
  
  ODS OUTPUT ParameterEstimates = _parm;  
  PROC LOGISTIC DATA = _out2;
    MODEL &y = p&event p&event.2 /  LINK = &link ;
  RUN;
     
  %IF &loop = 1 %THEN %DO;
  DATA _parm1;
    FORMAT link $10.;
    SET _parm(WHERE = (variable = "p&event.2"));
    LINK = UPCASE("&link");
  RUN;
  %END;
  %ELSE %DO;
  DATA _parm1;
    SET _parm1 _parm(WHERE = (variable = "p&event.2") IN = new);
    IF new then link = UPCASE("&link");
  RUN;
  %END;
   
  DATA _parm2(DROP = variable);
    SET _parm1;
    _t = estimate / stderr;
    _df = &df;
    _p = (1 - PROBT(abs(_t), _df)) * 2;
  run;
   
  %LET loop = %EVAL(&loop + 1);
%END;
 
TITLE;
PROC REPORT DATA = _last_ spacing = 1 HEADLINE NOWINDOWS SPLIT = "*";
  COLUMN(" * PREGIBON TEST FOR GOODNESS OF LINK
           * H0: THE LINK FUNCTION IS SPECIFIED CORRECTLY * "
         link _t _df _p);
  DEFINE link / "LINK FUNCTION" width = 15 order order = data;          
  DEFINE _t   / "T-VALUE"       width = 15 format = 12.4;
  DEFINE _df  / "DF"            width = 10;
  DEFINE _p   / "P-VALUE"       width = 15 format = 12.4;
RUN;
%MEND;

