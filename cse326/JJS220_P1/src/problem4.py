# -------------------------------------------------------------------------
'''
    Problem 4: compute sigmoid(wT X + b), the loss function, and the gradient.
    This is the vectorized version that handle multiple training examples X.
'''

import numpy as np # linear algebra
from scipy.sparse import diags
from scipy.sparse import csr_matrix

# --------------------------
def linear(w, b, X):
    '''
    w: d x 1, Logistic parameters
    b: 1 x 1, Logistic parameters
    X: d x m, m example feature vectors
    :return: Z = wT X + b
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    Z = csr_matrix.dot(w.T,X).astype("float64") + b
    return Z

# --------------------------
def sigmoid(Z):
    '''
    Z: 1 x m vector. wT X + b
    :return: A = sigmoid(Z)
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    X = np.exp(Z)
    Y = X/(1+X)
    return Y

# --------------------------
def loss(A, Y):
    '''
    A: 1 x m, sigmoid output on m training examples
    Y: 1 x m, labels of the m training examples
    :return: mean negative log-likelihood loss on m training examples.
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    Z = np.zeros(shape=A.shape)
    for c in range(0,A.shape[0]):
        Z[c] = np.multiply(-Y[c] , np.log(A[c])) - np.multiply((1-Y[c]) , np.log(1-A[c]))
    return np.mean(Z)

# --------------------------
def dZ(Z, Y):
    '''
    Z: 1 x m vector. wT X + b
    Y: 1 x m, label of X
    :return: 1 x m, the gradient of the negative log-likelihood loss on all samples wrt z.
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    top = np.multiply(-1,Y) + np.multiply(np.exp(Z), (1-Y))
    bot = 1 + np.exp(Z)
    return top/bot

# --------------------------
def dw(Z, X, Y):
    '''
    Z: 1 x m vector. wT X + b
    X: d x m, m example feature vectors. Should be in CSC scipy sparse matrix format
    Y: 1 x m, label of X
    :return: d x 1, the gradient of the negative log-likelihood loss on all samples wrt w.
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    return (csr_matrix.dot(X, dZ(Z,Y).T))/Y.shape[1]

# --------------------------
def db(Z, Y):
    '''
    Z: 1 x m vector. wT X + b
    Y: 1 x m, label of X
    :return: 1, the gradient of the negative log-likelihood loss on (X, Y) wrt w.
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    return np.asmatrix(np.sum(dZ(Z,Y))/len(Y))
