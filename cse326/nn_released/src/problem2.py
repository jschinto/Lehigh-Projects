# -------------------------------------------------------------------------
'''
    Problem 2: Implement a simple feedforward neural network, the details of the architecture is given in HW6.
'''

from problem1 import *
import numpy as np
from sklearn.metrics import accuracy_score

class NN:

    #--------------------------
    def __init__(self, dimensions, activation_funcs, loss_func, rand_seed = None):
        """
        Specify a L layer feedforward network.
        Design consideration: we don't include data in this neural network class.

        dimensions: list of L+1 integers , with dimensions[i+1] and dimensions[i]
                            being the number of rows and columns for the W at layer i+1.
                            dimensions[0] is the dimension of the data.
                            dimensions[L] is the dimension of output units.
        activation_funcs: dictionary with key=layer number, value = an activation class
        loss_func: loss function at the top layer
        rand_seed: set this to a number if you want deterministic experiments.
        """

        #########################################
        ## INSERT YOUR CODE HERE
        #########################################
        self.L = len(dimensions)
        self.dimensions = dimensions
        self.activation_funcs = activation_funcs
        self.loss_func = loss_func

        self.W = {}
        self.b = {}
        self.a = {}
        self.z = {}

        np.random.seed(rand_seed)
        for i in range(len(dimensions) - 1):
            self.W[i + 1] = np.random.randn(dimensions[i + 1], dimensions[i]) / np.sqrt(dimensions[i])
            self.b[i + 1] = np.zeros((dimensions[i + 1], 1))

    #--------------------------
    def forward(self, X):
        """
        Forward computation of activations at each layer.
        :param X: n x m matrix. m examples in n dimensional space.
        :return:  an n x m matrix (the activations at output layer)
        """
        #########################################
        ## INSERT YOUR CODE HERE
        #########################################
        self.a[0] = X
        for i in range(self.L - 1):
            self.z[i + 1] = np.dot(self.W[i + 1], self.a[i]) + self.b[i + 1]
            self.a[i + 1] = self.activation_funcs[i + 1].activate(self.z[i + 1])
        return self.a[self.L - 1]

    #--------------------------
    def backward(self, y):
        """
        Back propagation.
        Use the A and Z cached in forward
        :return: two dictionaries of gradients of W and b respectively.
                dW[i] is the gradient of the loss to W[i]
                db[i] is the gradient of the loss to b[i]
        """
        #########################################
        ## INSERT YOUR CODE HERE
        #########################################
        dW = {}
        db = {}
        for i in range(self.L - 1, 0, -1):
            d1 = self.loss_func.gradient(y, self.a[i])
            try:
                d2 = np.multiply(d1, self.activation_funcs[i].gradient(self.z[i]))
            except NotImplementedError:
                d2 = d1
            dW[i] = (1/(self.a[i].shape[1])) * d2 * self.a[i-1]
            db[i] = np.multiply(d1, np.multiply(d2, 1))
        return dW, db

    #--------------------------
    def train(self, **kwargs):
        """

        :param kwargs:
        :return: the loss at the final step
        """
        X_train = kwargs['Training X']
        Y_train = kwargs['Training Y']
        iter_num = kwargs['max_iters']
        eta = kwargs['Learning rate']

        record_every = kwargs['record_every']

        losses = []
        grad_norms = []

        # iterations of gradient descent
        for it in range(iter_num):
            #########################################
            ## INSERT YOUR CODE HERE
            #########################################
            self.forward(X_train)
            losses[it] = self.loss_func.loss(Y_train, self.a[self.L-1])
            dW, db = self.backward(Y_train)
            grad_norms[it] = {1:dW, 2:db}
            for i in range(self.L - 1):
                self.W[i + 1] = self.W[i + 1] - eta * dW[i + 1]
                self.b[i + 1] = self.b[i + 1] - eta * db[i + 1]

            if (it + 1) % record_every == 0:
                print('iterations = {}: loss = {}, gradient norms = {}'.format(it, losses[it], grad_norms[it]), end=' ')
                if 'Test X' in kwargs and 'Test Y' in kwargs:
                    prediction_accuracy = self.test(**kwargs)
                    print (', test error = {}'.format(prediction_accuracy))
                else:
                    print ()
        return losses, grad_norms

    #--------------------------
    def test(self, **kwargs):
        """
        Test accuracy of the trained model.
        :return: classification accuracy (for classification) or
                    MSE loss (for regression)
        """
        X_test = kwargs['Test X']
        Y_test = kwargs['Test Y']

        loss_func = kwargs['Test loss function name']

        output = self.forward(X_test)

        if loss_func == '0-1 error':
            predicted_labels = np.argmax(output, axis = 0)
            true_labels = np.argmax(Y_test, axis = 0)
            return 1.0 - accuracy_score(np.array(true_labels).flatten(), np.array(predicted_labels).flatten())
            # return 1.0 - np.where(true_labels == predicted_labels)[0].shape[0] / true_labels.shape[1]
        else:
            # return the Frobenius norm of the difference between y and y_hat, divided by (2m)
            return np.linalg.norm(output - Y_test) ** 2 / (2 * Y_test.shape[1])
