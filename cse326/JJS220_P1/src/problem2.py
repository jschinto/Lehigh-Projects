# -------------------------------------------------------------------------
'''
    Problem 2: reading data set from a file, and then split them into training, validation and test sets.

    The package for loading data in python
'''

import pickle
from sklearn import preprocessing
from sklearn.preprocessing import OneHotEncoder
import numpy as np # linear algebra
from scipy.sparse import csr_matrix, issparse
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)


def loadData(filename):
    with open(filename, 'rb') as f:
        (X, y) = pickle.load(f)
    return X, y

def splitData(data_matrix, split_ratio=[0.7, 0.1, 0.2]):
    '''
	data_matrix: columns are samples
    split_ratio: the ratio of examples go into the Training, Validation, and Test sets.
    Split the whole dataset into Training, Validation, and Test sets.
    :return: return Training, Validation, and Test sets.
    '''
    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    if (issparse(data_matrix)):
        data_matrix = data_matrix.todense()
    x = [int(np.floor(data_matrix.shape[1] * split_ratio[0])),int(np.floor(data_matrix.shape[1] * split_ratio[0]) + np.floor(data_matrix.shape[1] * split_ratio[1]))]
    return np.split(data_matrix, x, 1)
    