/* 
 * CS:APP Data Lab 
 * 
 * <Please put your name and userid here>
 * 
 * bits.c - Source file with your solutions to the Lab.
 *          This is the file you will hand in to your instructor.
 *
 * WARNING: Do not include the <stdio.h> header; it confuses the dlc
 * compiler. You can still use printf for debugging without including
 * <stdio.h>, although you might get a compiler warning. In general,
 * it's not good practice to ignore compiler warnings, but in this
 * case it's OK.  
 */

#if 0
/*
 * Instructions to Students:
 *
 * STEP 1: Read the following instructions carefully.
 */

You will provide your solution to the Data Lab by
editing the collection of functions in this source file.

INTEGER CODING RULES:
 
  Replace the "return" statement in each function with one
  or more lines of C code that implements the function. Your code 
  must conform to the following style:
 
  int Funct(arg1, arg2, ...) {
      /* brief description of how your implementation works */
      int var1 = Expr1;
      ...
      int varM = ExprM;

      varJ = ExprJ;
      ...
      varN = ExprN;
      return ExprR;
  }

  Each "Expr" is an expression using ONLY the following:
  1. Integer constants 0 through 255 (0xFF), inclusive. You are
      not allowed to use big constants such as 0xffffffff.
  2. Function arguments and local variables (no global variables).
  3. Unary integer operations ! ~
  4. Binary integer operations & ^ | + << >>
    
  Some of the problems restrict the set of allowed operators even further.
  Each "Expr" may consist of multiple operators. You are not restricted to
  one operator per line.

  You are expressly forbidden to:
  1. Use any control constructs such as if, do, while, for, switch, etc.
  2. Define or use any macros.
  3. Define any additional functions in this file.
  4. Call any functions.
  5. Use any other operations, such as &&, ||, -, or ?:
  6. Use any form of casting.
  7. Use any data type other than int.  This implies that you
     cannot use arrays, structs, or unions.

 
  You may assume that your machine:
  1. Uses 2s complement, 32-bit representations of integers.
  2. Performs right shifts arithmetically.
  3. Has unpredictable behavior when shifting if the shift amount
     is less than 0 or greater than 31.


EXAMPLES OF ACCEPTABLE CODING STYLE:
  /*
   * pow2plus1 - returns 2^x + 1, where 0 <= x <= 31
   */
  int pow2plus1(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     return (1 << x) + 1;
  }

  /*
   * pow2plus4 - returns 2^x + 4, where 0 <= x <= 31
   */
  int pow2plus4(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     int result = (1 << x);
     result += 4;
     return result;
  }

FLOATING POINT CODING RULES

For the problems that require you to implement floating-point operations,
the coding rules are less strict.  You are allowed to use looping and
conditional control.  You are allowed to use both ints and unsigneds.
You can use arbitrary integer and unsigned constants. You can use any arithmetic,
logical, or comparison operations on int or unsigned data.

You are expressly forbidden to:
  1. Define or use any macros.
  2. Define any additional functions in this file.
  3. Call any functions.
  4. Use any form of casting.
  5. Use any data type other than int or unsigned.  This means that you
     cannot use arrays, structs, or unions.
  6. Use any floating point data types, operations, or constants.


NOTES:
  1. Use the dlc (data lab checker) compiler (described in the handout) to 
     check the legality of your solutions.
  2. Each function has a maximum number of operations (integer, logical,
     or comparison) that you are allowed to use for your implementation
     of the function.  The max operator count is checked by dlc.
     Note that assignment ('=') is not counted; you may use as many of
     these as you want without penalty.
  3. Use the btest test harness to check your functions for correctness.
  4. Use the BDD checker to formally verify your functions
  5. The maximum number of ops for each function is given in the
     header comment for each function. If there are any inconsistencies 
     between the maximum ops in the writeup and in this file, consider
     this file the authoritative source.

/*
 * STEP 2: Modify the following functions according the coding rules.
 * 
 *   IMPORTANT. TO AVOID GRADING SURPRISES:
 *   1. Use the dlc compiler to check that your solutions conform
 *      to the coding rules.
 *   2. Use the BDD checker to formally verify that your solutions produce 
 *      the correct answers.
 */


#endif
//1
/* 
 * bitAnd - x&y using only ~ and | 
 *   Example: bitAnd(6, 5) = 4
 *   Legal ops: ~ |
 *   Max ops: 8
 *   Rating: 1
 */
int bitAnd(int x, int y) {
	/*uses DeMorgans law to find bitAnd*/
  	return ~(~x|~y);
}
/*
 * isTmax - returns 1 if x is the maximum, two's complement number,
 *     and 0 otherwise 
 *   Legal ops: ! ~ & ^ | +
 *   Max ops: 10
 *   Rating: 1
 */
int isTmax(int x) {
	/*using the fact that the max int + 1 can are perfect complements of each other, as well as checking for -1 case*/
	return !(~((x+1)^x)) & !!(x+1);
}
/* 
 * tmin - return minimum two's complement integer 
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 4
 *   Rating: 1
 */
int tmin(void) {
	/*the minimum integer is just 1 and 31 0s*/
  	return 1<<31;
}
/* 
 * evenBits - return word with all even-numbered bits set to 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 8
 *   Rating: 1
 */
int evenBits(void) {
	/*used the constant 0x55 to fill each byte*/
  	return (0x55<<24)|(0x55<<16)|(0x55<<8)|(0x55);
}
//2
/* 
 * anyEvenBit - return 1 if any even-numbered bit in word set to 1
 *   where bits are numbered from 0 (least significant) to 31 (most significant)
 *   Examples anyEvenBit(0xA) = 0, anyEvenBit(0xE) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 2
 */
int anyEvenBit(int x) {
	/*using the evenBit int, I can determine if 0 is returned by anding or not*/
	int y = (0x55<<24)|(0x55<<16)|(0x55<<8)|(0x55);
	x = x&y;
  	return !!x;
}
/* 
 * byteSwap - swaps the nth byte and the mth byte
 *  Examples: byteSwap(0x12345678, 1, 3) = 0x56341278
 *            byteSwap(0xDEADBEEF, 0, 2) = 0xDEEFBEAD
 *  You may assume that 0 <= n <= 3, 0 <= m <= 3
 *  Legal ops: ! ~ & ^ | + << >>
 *  Max ops: 25
 *  Rating: 2
 */
int byteSwap(int x, int n, int m) {
	int a;
	int b;
	int xn;
	int xm;
	/*using shifts to get the correct byte and then placing it into the other location*/
	n = n<<3;
	m = m<<3;
	a = 255<<n;
	b = 255<<m;
	xn = (x&a)>>(n)<<(m)&b;
	xm = (x&b)>>(m)<<(n)&a;
	x = (((x&~a&~b)|xn)|xm);
    return x;
}
/* 
 * dividePower2 - Compute x/(2^n), for 0 <= n <= 30
 *  Round toward zero
 *   Examples: dividePower2(15,1) = 7, dividePower2(-33,4) = -2
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 2
 */
int dividePower2(int x, int n) {
	/*using shifts to determine if x is negative and has odd values during the divides.  Then you add ones to each place in order to force rounding the other way.*/
    int sign = (x>>31);
	int odd = (1<<n) + (~0);
	int both = sign & odd;
	x = x + both;
	x = x>>n;
	return x;
}
//3
/* 
 * bitMask - Generate a mask consisting of all 1's 
 *   lowbit and highbit
 *   Examples: bitMask(5,3) = 0x38
 *   Assume 0 <= lowbit <= 31, and 0 <= highbit <= 31
 *   If lowbit > highbit, then mask should be all 0's
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 16
 *   Rating: 3
 */
int bitMask(int highbit, int lowbit) {
	int x;
	int y;
	/*creating a mask from the lowbit to the most significant bit and a mask from the high bit to least significant bit and then anding to find the overlap.*/
	x = ~0;
	y = x;
	x = x<<lowbit;
	y = y<<highbit<<1;
	y = ~y;
	x = x&y;
  	return x;
}
/* 
 * isAsciiDigit - return 1 if 0x30 <= x <= 0x39 (ASCII codes for characters '0' to '9')
 *   Example: isAsciiDigit(0x35) = 1.
 *            isAsciiDigit(0x3a) = 0.
 *            isAsciiDigit(0x05) = 0.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 3
 */
int isAsciiDigit(int x) {
	/*abusing the fact that the most significant bit is the signbit and setting differences to determine an upper and lower bound*/
	int signbit = 1<<31;
	int upper = ~(signbit | 0x39);
	int lower = (~0x30) + 1;
	upper = (upper + x) >> 31;
	lower = (lower + x) >> 31;
	return !(upper|lower);
}
/* 
 * isLessOrEqual - if x <= y  then return 1, else return 0 
 *   Example: isLessOrEqual(4,5) = 1.
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 24
 *   Rating: 3
 */
int isLessOrEqual(int x, int y) {
	/*comparing signs to deal with overflow and then checking if y-x is positive or negative*/
	int signx = x>>31;
	int signy = y>>31;
	int invx = ~x+1;
	int sub = y+invx;
	sub = sub>>31;
  	return (!sub&!(signx^signy))|(!!signx&!signy);
}
//4
/*
 * bitParity - returns 1 if x contains an odd number of 0's
 *   Examples: bitParity(5) = 0, bitParity(7) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 20
 *   Rating: 4
 */
int bitParity(int x) {
	/*abusing xor to flip the bit values on themselves and find if the result is a 1 or 0 (odd or even)*/
	x = x ^ (x>>16);
	x = x ^ (x>>8);
	x = x ^ (x>>4);
	x = x ^ (x>>2);
	x = x ^ (x>>1);
	x = x & 1;
  	return x;
}
/* 
 * bang - Compute !x without using !
 *   Examples: bang(3) = 0, bang(0) = 1
 *   Legal ops: ~ & ^ | + << >>
 *   Max ops: 12
 *   Rating: 4 
 */
int bang(int x) {
	/*using the fact that 0 is the only int with no negative version of itself to find if x is zero.  Then add 1 to -1 or 0 for the return*/
	int neg = ~x+1;
	int check = neg|x;
	check = check>>31;
  	return check+1;
}
//float
/* 
 * floatAbsVal - Return bit-level equivalent of absolute value of f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representations of
 *   single-precision floating point values.
 *   When argument is NaN, return argument..
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 10
 *   Rating: 2
 */
unsigned floatAbsVal(unsigned uf) {
	/*value is nan if exponent is all ones and data !=0.  Then you just need to set the sign to 0.*/
	unsigned exp = 255<<23;
	unsigned a = uf&(exp);
	unsigned b = uf<<9;
	if(b!=0 && a==(exp)) {
		return uf;
	}
  	uf = ~uf;
	uf = uf|(1<<31);
	uf = ~uf;
	return uf;
}
/* 
 * floatIsLess - Compute f < g for floating point arguments f and g.
 *   Both the arguments are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representations of
 *   single-precision floating point values.
 *   If either argument is NaN, return 0.
 *   +0 and -0 are considered equal.
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 3
 */
int floatIsLess(unsigned uf, unsigned ug) {
	/*using ifs to compare edge cases and then simply compare unsigned values because the way the data is laid out (exponent > rest of data) will lead to a higher value if the float is larger*/
    unsigned exp = 255<<23;
    unsigned a = uf&(exp);
    unsigned b = (uf<<9);
	unsigned c = ug&(exp);
    unsigned d = (ug<<9);
	unsigned signf = uf>>31;
	unsigned signg = ug>>31;
	unsigned compf = uf<<1;
	unsigned compg = ug<<1;
    if((b!=0 && a==(exp)) || (d!=0 && c==(exp))) {
        return 0;
    }
	if((b==0&&a==0) && (d==0&&c==0)) {
		return 0;
	}
	if(signg < signf) {
		return 1;
	}
	if(signg > signf) {
        return 0;
    }
	if(signf==1) {
		if(compf>compg) {
			return 1;
		}
		return 0;
	}
	if(compg>compf) {
		return 1;
	}
	return 0;
}
/* 
 * floatScale2 - Return bit-level equivalent of expression 2*f for
 *   floating point argument f.
 *   Both the argument and result are passed as unsigned int's, but
 *   they are to be interpreted as the bit-level representation of
 *   single-precision floating point values.
 *   When argument is NaN, return argument
 *   Legal ops: Any integer/unsigned operations incl. ||, &&. also if, while
 *   Max ops: 30
 *   Rating: 4
 */
unsigned floatScale2(unsigned uf) {
	/*the trick here is determining when the float is denormalized and seeing if a simple bitshift to the data could work or you need to make it normalized.  Otherwise you just simply increment the exponent*/
	unsigned exp = 255<<23;
    unsigned a = uf&(exp);
    unsigned b = (uf<<9);
	unsigned denorm = b>>31;
    if(a==(exp)) {
        return uf;
    }
    if(a==0) {
		if(denorm==1) {
			uf = uf&~exp;
			uf = uf|(a+0x800000);
		}
		uf = (uf>>23) << 23;
		uf = uf|((b<<1) >> 9);
		return uf;
    }
	uf = uf&~exp;
    uf = uf|(a+0x800000);
    return uf;
}
