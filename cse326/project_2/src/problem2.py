# -------------------------------------------------------------------------
'''
    Problem 2: Compute the objective function and decision function of dual SVM.

'''
from problem1 import *

import numpy as np

# -------------------------------------------------------------------------
def dual_objective_function(alpha, train_y, train_X, kernel_function, sigma):
    """
    Compute the dual objective function value.
    alpha: 1 x m learned Lagrangian multipliers (the dual variables).
    train_y: 1 x m labels (-1 or 1) of training data.
    train_X: n x m training feature matrix.
    kernel_function: as the name suggests
    sigma: need to be provided when Gaussian kernel is used.
    :return: a scalar representing the dual objective function value at alpha
    """
    
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    result = 0
    a = np.asmatrix(alpha)
    train_y = np.asmatrix(train_y)
    train_X = np.asmatrix(train_X)
    m = train_y.shape[1]
    for i in range(m):
        for j in range(m):
            args = (train_X[:,i],train_X[:,j])
            if (kernel_function == Gaussian_kernel):
                args = (train_X[:,i],train_X[:,j],sigma)
            result += a[0,i]*a[0,j]*train_y[0,i]*train_y[0,j]*kernel_function(*args)
    return np.sum(a) - ((1/2)*result)

# -------------------------------------------------------------------------
def primal_objective_function(alpha, train_y, train_X, b, C, kernel_function, sigma):
    """
    Compute the primal objective function value.
    alpha: 1 x m learned Lagrangian multipliers (the dual variables).
    train_y: 1 x m labels (-1 or 1) of training data.
    train_X: n x m training feature matrix.
    b: bias term
    C: regularization parameter of soft-SVM
    kernel_function: as the name suggests
    sigma: need to be provided when Gaussian kernel is used.
    :return: a scalar representing the primal objective function value at alpha
    """
    
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    z = decision_function(alpha, train_y, train_X, b, kernel_function, sigma, train_X)
    h = np.sum(hinge_loss(z, train_y))
    w = np.sum(np.multiply(alpha,np.multiply(train_y,train_X)), 1)
    w = np.dot(w.T,w)
    if (kernel_function == Gaussian_kernel):
        w = np.multiply(alpha, train_y)*kernel_function(train_X, train_X, sigma)*np.multiply(alpha, train_y).T
    w = (1/2)*w
    return (C*h) + w

# -------------------------------------------------------------------------
def decision_function(alpha, train_y, train_X, b, kernel_function, sigma, test_X):
    """
    Predict the labels of test_X, using the current SVM.

    alpha: 1 x m learned Lagrangian multipliers (the dual variables).
    train_y: 1 x m labels (-1 or 1) of training data.
    train_X: n x m training feature matrix.
    b: scalar, the bias term in SVM w^T x + b.
    kernel_function: as the name suggests
    sigma: need to be provided when Gaussian kernel is used.
    test_X: n x m2 test feature matrix.
    :return: 1 x m2 vector w^T x + b
    """
    
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    a = np.asmatrix(alpha)
    y = np.asmatrix(train_y)
    x = np.asmatrix(train_X)
    test_X = np.asmatrix(test_X)
    args = (x, test_X)
    if (kernel_function == Gaussian_kernel):
        args = (x, test_X, sigma)
    ans = np.sum(np.multiply(a,np.multiply(y,kernel_function(*args))),1) + b    
    return np.asarray(ans.T)