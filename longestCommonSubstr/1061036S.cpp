#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <ctime>
#include <cmath>
#include "IOforHW.cpp"
using namespace std;


/**
 *  @param front should be at least larger than 0
 */
int compute_l(int front, int length, vector<int>& seqA, vector<int>& seqB) {
    vector< vector<int> > all_l(length+1, vector<int>(length+1, -1));
    /* base case */
    for(int i=0; i<=length; i++) {
        all_l[i][0] = 0;
        all_l[0][i] = 0;
    }
    /* compute all possible lengths of all commmon subsequences */
    front--;
    for(int i=1; i<=length; i++) {
        for(int j=1; j<= length; j++) {
            if(all_l[i][j] == -1) {
                if(seqA[i+front] == seqB[j+front]) {
                    all_l[i][j] = all_l[i-1][j-1] + 1;
                }else {
                    all_l[i][j] = max(all_l[i-1][j], all_l[i][j-1]);
                }
            } 
        }
    }
    return all_l[length][length];
}

int main() {
    int result;
    int length; //The length of subsequences of the two sueqences that are compared. 
    vector<int> seqA;
    vector<int> seqB;
    IOforHW io;
    /*
    length = 6;
    vector<int> seqA = {-1,1,2,4,5,5,3};  //remember to insert void element at front to let the sequence start from 1
    vector<int> seqB = {-1,1,2,3,1,4,5};
    */
    //io.inputInit();
    io.readInput(length, seqA, seqB);
    seqA.insert(seqA.begin(), -1);
    seqB.insert(seqB.begin(), -1);
    result = compute_l(1,length, seqA, seqB);
    io.resultOut(result);
    //printf("length of LCS is %d.\n", result);
}
