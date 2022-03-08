# -------------------------------------------------------------------------
'''
    Problem 5: Gradient Descent Training of Logistic Regression
'''

from problem4 import * # compute loss and grad
from problem2 import *
import numpy as np # linear algebra
import pickle
# --------------------------
def gradient_descent(X, Y, X_val, Y_val, X_test, Y_test, num_iters = 50, lr = 0.01, log=True):
    '''
    Train Logistic Regression using Gradient Descent
    X: d x m training sample vectors (sparse csc_matrix)
    Y: 1 x m labels
    X_val: validation sample vectors (sparse csc_matrix)
    Y_val: validation labels
    X_test: test sample vectors (sparse csc_matrix)
    Y_test: test labels
    num_iters: number of gradient descent iterations
    lr: learning rate
    log: True if you want to track the training process, by default True
    :return: (w, b, training_log)
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    training_log = []
    w = np.asmatrix(np.zeros(shape=(X.shape[0], 1)))
    b = np.asmatrix(np.zeros(shape=(1, 1)))
    _loss = np.float64(0.0)
    _val_loss = np.float64(0.0)
    _test_loss = np.float64(0.0)
    _dw = np.zeros(shape=(X.shape[0], 1))
    _db = np.zeros(shape=(1, 1))
    for i in range(num_iters):
        Z = linear(w,b,X)
        vZ = linear(w,b,X_val)
        tZ = linear(w,b,X_test)
        A = sigmoid(Z)
        vA = sigmoid(vZ)
        tA = sigmoid(tZ)
        _loss = loss(A,Y)
        _val_loss = loss(vA,Y_val)
        _test_loss = loss(tA,Y_test)
        _dw = lr*(dw(Z,X,Y)/X.shape[1])
        _db = lr*(db(Z,Y)/X.shape[1])
        w-=_dw
        b-=_db
        if(log):
            training_log.append((_loss, _val_loss, _test_loss, np.linalg.norm(_dw)**2 + np.linalg.norm(_db)**2))
    return [w,b,training_log]

# --------------------------
def train(**kwargs):
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    return gradient_descent(kwargs['Training X'],kwargs['Training y'],
    kwargs['Validation X'],kwargs['Validation y'],kwargs['Test X'],
    kwargs['Test y'],kwargs['num_iters'],kwargs['lr'],
    kwargs['log'])

# --------------------------
if __name__ == "__main__":
    filename = '../data/data_matrices.pkl'
    X, y = loadData(filename)
    tr_X, val_X, te_X = splitData(X)
    tr_y, val_y, te_y = splitData(y)

    kwargs = {'Training X': tr_X,
              'Training y': tr_y,
              'Validation X': val_X,
              'Validation y': val_y,
              'Test X': te_X,
              'Test y': te_y,
              'num_iters': 20,
              'lr': 0.01,
              'log': True}

    w, b, training_log = train(**kwargs)

    with open('../data/batch_outcome.pkl', 'wb') as f:
        pickle.dump((w, b, training_log), f)
