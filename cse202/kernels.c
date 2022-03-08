/********************************************************
 * Kernels to be optimized for the CS:APP Performance Lab
 ********************************************************/

#include <stdio.h>
#include <stdlib.h>
#include "defs.h"

/* 
 * Please fill in the following team struct 
 */
team_t team = {
    "jjs220",              /* Team name */

    "Jake Schinto",     /* First member full name */
    "jjs220@lehigh.edu",  /* First member email address */

    "",                   /* Second member full name (leave blank if none) */
    ""                    /* Second member email addr (leave blank if none) */
};

/***************
 * ROTATE KERNEL
 ***************/

/******************************************************
 * Your different versions of the rotate kernel go here
 ******************************************************/

/* 
 * naive_rotate - The naive baseline version of rotate 
 */
char naive_rotate_descr[] = "naive_rotate: Naive baseline implementation";
void naive_rotate(int dim, pixel *src, pixel *dst) 
{
    int i, j;

    for (i = 0; i < dim; i++)
	for (j = 0; j < dim; j++)
	    dst[RIDX(dim-1-j, i, dim)] = src[RIDX(i, j, dim)];
}

/* 
 * rotate - Your current working version of rotate
 * IMPORTANT: This is the version you will be graded on
 */
char rotate_descr[] = "rotate: Current working version";
void rotate(int dim, pixel *src, pixel *dst) 
{
    int i, j, jj;

    for (i = 0; i < dim; i+=16) {
		int val3 = i*dim;
        for (j = 0; j < dim; j+=16) {
			int val4 = ((dim-1-j)*dim);
            for (jj = j; jj < j + 16; jj++) {
                int val1 = val3;
				int val2 = val4+i;
                dst[val2] = src[val1+jj];
				val1+=dim;
                dst[val2+1] = src[val1+jj];
                val1+=dim;
                dst[val2+2] = src[val1+jj];
                val1+=dim;
                dst[val2+3] = src[val1+jj];
                val1+=dim;
                dst[val2+4] = src[val1+jj];
                val1+=dim;
                dst[val2+5] = src[val1+jj];
                val1+=dim;
                dst[val2+6] = src[val1+jj];
                val1+=dim;
                dst[val2+7] = src[val1+jj];
                val1+=dim;
                dst[val2+8] = src[val1+jj];
                val1+=dim;
                dst[val2+9] = src[val1+jj];
                val1+=dim;
                dst[val2+10] = src[val1+jj];
                val1+=dim;
                dst[val2+11] = src[val1+jj];
                val1+=dim;
                dst[val2+12] = src[val1+jj];
                val1+=dim;
                dst[val2+13] = src[val1+jj];
                val1+=dim;
                dst[val2+14] = src[val1+jj];
                val1+=dim;
                dst[val2+15] = src[val1+jj];
				val4-=dim;
            }
		}
    }
}

/*********************************************************************
 * register_rotate_functions - Register all of your different versions
 *     of the rotate kernel with the driver by calling the
 *     add_rotate_function() for each test function. When you run the
 *     driver program, it will test and report the performance of each
 *     registered test function.  
 *********************************************************************/

void register_rotate_functions() 
{
    add_rotate_function(&naive_rotate, naive_rotate_descr);   
    add_rotate_function(&rotate, rotate_descr);   
    /* ... Register additional test functions here */
}


/***************
 * SMOOTH KERNEL
 **************/

/***************************************************************
 * Various typedefs and helper functions for the smooth function
 * You may modify these any way you like.
 **************************************************************/

/* A struct used to compute averaged pixel value */
typedef struct {
    int red;
    int green;
    int blue;
    int num;
} pixel_sum;

/* Compute min and max of two integers, respectively */
static int min(int a, int b) { return (a < b ? a : b); }
static int max(int a, int b) { return (a > b ? a : b); }

/* 
 * initialize_pixel_sum - Initializes all fields of sum to 0 
 */
static void initialize_pixel_sum(pixel_sum *sum) 
{
    sum->red = sum->green = sum->blue = 0;
    sum->num = 0;
    return;
}

/* 
 * accumulate_sum - Accumulates field values of p in corresponding 
 * fields of sum 
 */
static void accumulate_sum(pixel_sum *sum, pixel p) 
{
    sum->red += (int) p.red;
    sum->green += (int) p.green;
    sum->blue += (int) p.blue;
    sum->num++;
    return;
}

/* 
 * assign_sum_to_pixel - Computes averaged pixel value in current_pixel 
 */
static void assign_sum_to_pixel(pixel *current_pixel, pixel_sum sum) 
{
    current_pixel->red = (unsigned short) (sum.red/sum.num);
    current_pixel->green = (unsigned short) (sum.green/sum.num);
    current_pixel->blue = (unsigned short) (sum.blue/sum.num);
    return;
}

/* 
 * avg - Returns averaged pixel value at (i,j) 
 */
static pixel avg(int dim, int i, int j, pixel *src) 
{
    int ii, jj;
    pixel_sum sum;
    pixel current_pixel;

    initialize_pixel_sum(&sum);
    for(ii = max(i-1, 0); ii <= min(i+1, dim-1); ii++) 
	for(jj = max(j-1, 0); jj <= min(j+1, dim-1); jj++) 
	    accumulate_sum(&sum, src[RIDX(ii, jj, dim)]);

    assign_sum_to_pixel(&current_pixel, sum);
    return current_pixel;
}

/******************************************************
 * Your different versions of the smooth kernel go here
 ******************************************************/

/*
 * naive_smooth - The naive baseline version of smooth 
 */
char naive_smooth_descr[] = "naive_smooth: Naive baseline implementation";
void naive_smooth(int dim, pixel *src, pixel *dst) 
{
    int i, j;

    for (i = 0; i < dim; i++)
	for (j = 0; j < dim; j++)
	    dst[RIDX(i, j, dim)] = avg(dim, i, j, src);
}

/*
 * smooth - Your current working version of smooth. 
 * IMPORTANT: This is the version you will be graded on
 */
char smooth_descr[] = "smooth: Current working version";
void smooth(int dim, pixel *src, pixel *dst) 
{
	//remove avg function
	//Top Row
	int i,j;
    int curr = 0;
	dst[curr].red = (src[curr].red + src[curr+1].red + src[curr+dim].red + src[curr+dim+1].red)/4;
	dst[curr].green = (src[curr].green + src[curr+1].green + src[curr+dim].green + src[curr+dim+1].green)/4;
    dst[curr].blue = (src[curr].blue + src[curr+1].blue + src[curr+dim].blue + src[curr+dim+1].blue)/4;
	for (i = 1; i < dim-1; i++) {
		curr++;
		dst[curr].red = (src[curr-1].red + src[curr].red + src[curr+1].red + src[curr+dim-1].red + src[curr+dim].red + src[curr+dim+1].red)/6;
		dst[curr].green = (src[curr-1].green + src[curr].green + src[curr+1].green + src[curr+dim-1].green + src[curr+dim].green + src[curr+dim+1].green)/6;
		dst[curr].blue = (src[curr-1].blue + src[curr].blue + src[curr+1].blue + src[curr+dim-1].blue + src[curr+dim].blue + src[curr+dim+1].blue)/6;
	}
	curr++;
	dst[curr].red = (src[curr-1].red + src[curr].red + src[curr+dim-1].red + src[curr+dim].red)/4;
	dst[curr].green = (src[curr-1].green + src[curr].green + src[curr+dim-1].green + src[curr+dim].green)/4;
    dst[curr].blue = (src[curr-1].blue + src[curr].blue + src[curr+dim-1].blue + src[curr+dim].blue)/4;

	//Middle Rows
	for (i = 1; i < dim-1; i++) {
		for (j = 0; j < dim; j++) {
			curr++;
			//edges moved to end
/*			if (j==0) {
				dst[curr].red = (src[curr-dim].red + src[curr-dim+1].red + src[curr].red + src[curr+1].red + src[curr+dim].red + src[curr+dim+1].red)/6;
				dst[curr].green = (src[curr-dim].green + src[curr-dim+1].green + src[curr].green + src[curr+1].green + src[curr+dim].green + src[curr+dim+1].green)/6;
				dst[curr].blue = (src[curr-dim].blue + src[curr-dim+1].blue + src[curr].blue + src[curr+1].blue + src[curr+dim].blue + src[curr+dim+1].blue)/6;
			}
			if (j==dim-1) {
				dst[curr].red = (src[curr-dim-1].red + src[curr-dim].red + src[curr-1].red + src[curr].red + src[curr+dim-1].red + src[curr+dim].red)/6;
				dst[curr].green = (src[curr-dim-1].green + src[curr-dim].green + src[curr-1].green + src[curr].green + src[curr+dim-1].green + src[curr+dim].green)/6;
				dst[curr].blue = (src[curr-dim-1].blue + src[curr-dim].blue + src[curr-1].blue + src[curr].blue + src[curr+dim-1].blue + src[curr+dim].blue)/6;
			}*/
			if (j>0 && j<dim-1) {
				dst[curr].red = (src[curr-dim-1].red + src[curr-dim].red + src[curr-dim+1].red + src[curr-1].red + src[curr].red + src[curr+1].red + src[curr+dim-1].red + src[curr+dim].red + src[curr+dim+1].red)/9;
				dst[curr].green = (src[curr-dim-1].green + src[curr-dim].green + src[curr-dim+1].green + src[curr-1].green + src[curr].green + src[curr+1].green + src[curr+dim-1].green + src[curr+dim].green + src[curr+dim+1].green)/9;
				dst[curr].blue = (src[curr-dim-1].blue + src[curr-dim].blue + src[curr-dim+1].blue + src[curr-1].blue + src[curr].blue + src[curr+1].blue + src[curr+dim-1].blue + src[curr+dim].blue + src[curr+dim+1].blue)/9;
			}
		}
	}

	//Bottom Row
	curr++;
	dst[curr].red = (src[curr-dim].red + src[curr-dim+1].red + src[curr].red + src[curr+1].red)/4;
	dst[curr].green = (src[curr-dim].green + src[curr-dim+1].green + src[curr].green + src[curr+1].green)/4;
    dst[curr].blue = (src[curr-dim].blue + src[curr-dim+1].blue + src[curr].blue + src[curr+1].blue)/4;
	for (i = 1; i < dim-1; i++) {
		curr++;
		dst[curr].red = (src[curr-dim-1].red + src[curr-dim].red + src[curr-dim+1].red + src[curr-1].red + src[curr].red + src[curr+1].red)/6;
		dst[curr].green = (src[curr-dim-1].green + src[curr-dim].green + src[curr-dim+1].green + src[curr-1].green + src[curr].green + src[curr+1].green)/6;
		dst[curr].blue = (src[curr-dim-1].blue + src[curr-dim].blue + src[curr-dim+1].blue + src[curr-1].blue + src[curr].blue + src[curr+1].blue)/6;
	}
	curr++;
	dst[curr].red = (src[curr-dim-1].red + src[curr-dim].red + src[curr-1].red + src[curr].red)/4;
	dst[curr].green = (src[curr-dim-1].green + src[curr-dim].green + src[curr-1].green + src[curr].green)/4;
    dst[curr].blue = (src[curr-dim-1].blue + src[curr-dim].blue + src[curr-1].blue + src[curr].blue)/4;

	//Side edges: faster at end
	curr = dim;
  	for (i = 1; i < dim-1; i++) {
		dst[curr].red = (src[curr-dim].red + src[curr-dim+1].red + src[curr].red + src[curr+1].red + src[curr+dim].red + src[curr+dim+1].red)/6;
		dst[curr].green = (src[curr-dim].green + src[curr-dim+1].green + src[curr].green + src[curr+1].green + src[curr+dim].green + src[curr+dim+1].green)/6;
		dst[curr].blue = (src[curr-dim].blue + src[curr-dim+1].blue + src[curr].blue + src[curr+1].blue + src[curr+dim].blue + src[curr+dim+1].blue)/6;

    	curr += dim-1;
		dst[curr].red = (src[curr-dim-1].red + src[curr-dim].red + src[curr-1].red + src[curr].red + src[curr+dim-1].red + src[curr+dim].red)/6;
		dst[curr].green = (src[curr-dim-1].green + src[curr-dim].green + src[curr-1].green + src[curr].green + src[curr+dim-1].green + src[curr+dim].green)/6;
		dst[curr].blue = (src[curr-dim-1].blue + src[curr-dim].blue + src[curr-1].blue + src[curr].blue + src[curr+dim-1].blue + src[curr+dim].blue)/6;
		curr++;
	}
}

/********************************************************************* 
 * register_smooth_functions - Register all of your different versions
 *     of the smooth kernel with the driver by calling the
 *     add_smooth_function() for each test function.  When you run the
 *     driver program, it will test and report the performance of each
 *     registered test function.  
 *********************************************************************/

void register_smooth_functions() {
    add_smooth_function(&smooth, smooth_descr);
    add_smooth_function(&naive_smooth, naive_smooth_descr);
    /* ... Register additional test functions here */
}

