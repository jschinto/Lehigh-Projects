# -------------------------------------------------------------------------
'''
    Problem 3: compute sigmoid(wTx+b), the loss function, and the gradient.
    This is the single training example version.
'''

import numpy as np # linear algebra

# --------------------------
def linear(w, b, x):
    '''
    w: d x 1, Logistic parameters
    b: 1 x 1, Logistic parameters
    x: d x 1, an example feature vector. Must be a sparse csc_matrix
    :return: wT x + b
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    return np.dot(w.T,x) + b

# --------------------------
def sigmoid(z):
    '''
    z: scalar. wT x + b
    :return: sigmoid(z)
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    x = np.exp(z)
    return x/(1+x)

# --------------------------
def loss(a, y):
    '''
    a: 1 x 1, sigmoid of the example (x, y)
    y: {0,1}, label of x
    :return: negative log-likelihood loss on (x, y).
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    return (-y * np.log(a)) - ((1-y) * np.log(1-a))

# --------------------------
def dz(z, y):
    '''
    z: scalar. wT x + b
    y: {0,1}, label of x
    :return: the gradient of the negative log-likelihood loss on (x, y) wrt z.
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    #-loss(sigmoid(z), y)
    top = (-y) + (np.exp(z)*(-y+1))
    bot = 1 + np.exp(z)
    return top/bot

# --------------------------
def dw(z, x, y):
    '''
    z: scalar. wT x + b
    x: d x 1, an example feature vector
    y: {0,1}, label of x
    :return: the gradient of the negative log-likelihood loss on (x, y) wrt w.
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    return x * dz(z, y)

# --------------------------
def db(z, y):
    '''
    z: scalar. wT x + b
    y: {0,1}, label of x
    :return: the gradient of the negative log-likelihood loss on (x, y) wrt w.
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    return dz(z,y)
