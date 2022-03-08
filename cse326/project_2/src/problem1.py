# -------------------------------------------------------------------------
'''
    Problem 1: Implement linear, Gaussian kernels, and hinge loss

'''

import numpy as np
from sklearn.metrics.pairwise  import euclidean_distances

# --------------------------
def linear_kernel(X1, X2):
    """
    Compute linear kernel between two set of feature vectors.
    No constant 1 is appended.
    X1: n x m1 matrix, each of the m1 column is an n-dim feature vector.
    X2: n x m2 matrix, each of the m2 column is an n-dim feature vector.
    :return: if both m1 and m2 are 1, return linear kernel on the two vectors; else return a m1 x m2 kernel matrix K,
            where K(i,j)=linear kernel of column i from X1 and column j from X2
    """

    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    return np.dot(X1.T, X2)

# --------------------------
def Gaussian_kernel(X1, X2, sigma):
    """
    Compute linear kernel between two set of feature vectors.
    No constant 1 is appended.
    For your convenience, please use euclidean_distances.

    X1: n x m1 matrix, each of the m1 column is an n-dim feature vector.
    X2: n x m2 matrix, each of the m2 column is an n-dim feature vector.
    sigma: Gaussian variance (called bandwidth)
    :return: if both m1 and m2 are 1, return Gaussian kernel on the two vectors; else return a m1 x m2 kernel matrix K,
            where K(i,j)=Gaussian kernel of column i from X1 and column j from X2

    """
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    return np.exp((-1/(2*(sigma**2))) * (euclidean_distances(X1.T, X2.T)**2))

# --------------------------
def hinge_loss(z, y):
    """
    Compute the hinge loss on a set of training examples
    z: 1 x m vector, each entry is <w, x> + b (may come from a kernel function)
    y: 1 x m label vector. Each entry is -1 or 1
    :return: 1 x m hinge losses over the m examples
    """
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    z = np.asmatrix(z)
    y = np.asmatrix(y)
    l = np.maximum(0, 1 - np.multiply(y,z))
    return l
