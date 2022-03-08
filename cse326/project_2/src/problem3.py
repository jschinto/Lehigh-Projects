# -------------------------------------------------------------------------
'''
    Problem 3: SMO training algorithm

'''
from problem1 import *
from problem2 import *

import numpy as np

import copy

# -------------------------------------------------------------------------
class SVMModel():
    """
    The class containing information about the SVM model, including parameters, data, and hyperparameters
    """
    def __init__(self, train_X, train_y, C, kernel_function, sigma=1):
        # data
        self.train_X = train_X
        self.train_y = train_y
        self.n, self.m = train_X.shape

        # hyper-parameters
        self.C = C
        self.kernel_func = kernel_function
        self.sigma = sigma

        # parameters
        self.alpha = np.zeros((1, self.m))
        self.b = 0

# -------------------------------------------------------------------------
def train(model, max_iters = 10, record_every = 1, max_passes = 1, tol=1e-6):
    """
    SMO training of SVM
    model: an SVMModel
    max_iters: how many iterations of optimization
    record_every: record intermediate dual and primal objective values and models every record_every iterations
    max_passes: each iteration can have maximally max_passes without change any alpha
    tol: numerical tolerance (exact equality of two floating numbers may be impossible).
    :return: 3 lists (of dual objectives, primal objectives, and models)
    """

    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    iter_nums = []
    dual_objectives = []
    primal_objectives = []
    models = []
    model.alpha = np.asmatrix(model.alpha)
    for iters in range(max_iters):
        passes = 0
        while(passes < max_passes):
            num_changed_alpha = 0
            for i in range(model.m):
                ei = decision_function(model.alpha, model.train_y, model.train_X, model.b, model.kernel_func, model.sigma, model.train_X)[:,i] - model.train_y[:,i]
                yei = np.dot(model.train_y[:,i],ei)
                if(((yei < -tol) and (model.alpha[:,i] < model.C)) or ((yei > tol) and (model.alpha[:,i] > 0))):
                    j = np.random.randint(0, model.m)
                    while(i == j):
                        j = np.random.randint(0, model.m)
                    ej = decision_function(model.alpha, model.train_y, model.train_X, model.b, model.kernel_func, model.sigma, model.train_X)[:,j] - model.train_y[:,j]
                    ai = model.alpha[:,i]
                    aj = model.alpha[:,j]
                    if(model.train_y[:,i] == model.train_y[:,j]):
                        L = max(0, aj+ai-model.C)
                        H = min(model.C, aj+ai)
                    else:
                        L = max(0, aj-ai)
                        H = min(model.C, model.C+aj-ai)
                    if(L == H):
                        continue
                    args1 = (model.train_X[:,i], model.train_X[:,i])
                    args2 = (model.train_X[:,j], model.train_X[:,j])
                    args3 = (model.train_X[:,i], model.train_X[:,j])
                    if(model.kernel_func == Gaussian_kernel):
                        args1 = (model.train_X[:,i], model.train_X[:,i], model.sigma)
                        args2 = (model.train_X[:,j], model.train_X[:,j], model.sigma)
                        args3 = (model.train_X[:,i], model.train_X[:,j], model.sigma)
                    n = (2 * model.kernel_func(*args3)) - model.kernel_func(*args1) - model.kernel_func(*args2)
                    if(n >= 0):
                        continue
                    ajn = aj + ((model.train_y[:,j]*(ej-ei))/n)
                    if(ajn > H):
                        ajn = H
                    elif(ajn < L):
                        ajn = L
                    if(abs(aj - ajn) < tol):
                        continue
                    model.alpha[:,j] = ajn
                    s = model.train_y[:,i] * model.train_y[:,j]
                    #print(model.train_y[:,i].shape)
                    #print(model.alpha[:,i].shape)
                    #model.alpha[:,i] = ai + (model.train_y[:,i]*model.train_y[:,j]*(aj - model.alpha[:,j]))
                    #print(model.train_y[:,i])
                    model.alpha[:,i] = ai - s * (model.alpha[:,j] - aj)
                    if(model.alpha[:,i] < 0):
                        model.alpha[:,j] += s * model.alpha[:,i]
                        model.alpha[:,i] = 0
                    elif(model.alpha[:,i] > model.C):
                        t = model.alpha[:,i] - model.C
                        model.alpha[:,j] += s * t
                        model.alpha[:,i] = model.C
                    bi = model.b - ei - model.train_y[:,i]*(model.alpha[:,i] - ai)*model.kernel_func(*args1) - model.train_y[:,j]*(model.alpha[:,j] - aj)*model.kernel_func(*args3)
                    bj = model.b - ei - model.train_y[:,i]*(model.alpha[:,i] - ai)*model.kernel_func(*args3) - model.train_y[:,j]*(model.alpha[:,j] - aj)*model.kernel_func(*args2)
                    if(ai > 0 and ai < model.C):
                        model.b = bi
                    elif(aj > 0 and aj < model.C):
                        model.b = bj
                    else:
                        model.b = (bi + bj)/2
                    num_changed_alpha += 1
            if(num_changed_alpha == 0):
                passes += 1
            else:
                passes = 0
        if(iters%record_every == 0):
            iter_nums.append(iters+1)
            dual_objectives.append(dual_objective_function(model.alpha, model.train_y, model.train_X, model.kernel_func, model.sigma))
            primal_objectives.append(primal_objective_function(model.alpha, model.train_y, model.train_X, model.b, model.C, model.kernel_func, model.sigma))
            models.append(model)
    #print(iter_nums)
    #print(dual_objectives)
    #print(primal_objectives)
    #print(len(models))
    return iter_nums, dual_objectives, primal_objectives, models

# -------------------------------------------------------------------------
def predict(model, test_X):
    """
    Predict the labels of test_X
    model: an SVMModel
    test_X: n x m matrix, test feature vectors
    :return: 1 x m matrix, predicted labels
    """

    #########################################
    ## INSERT YOUR CODE HERE
    #########################################
    return np.sign(decision_function(model.alpha, model.train_y, model.train_X, model.b, model.kernel_func, model.sigma, test_X))
