//
//  main.cpp
//  prime_guage
//
//  Created by Wayne Chen on 2017/9/22.
//  Copyright © 2017年 Wayne Chen. All rights reserved.
//
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

class prime_guage {
public:
  int inputInit();
  void primeGuage();
  int readInput();
  void insertionSort();
  int resultOut();
private:
  bool primeFlag = true;
  long long n;
  vector<long long> factors;
};

int prime_guage::inputInit() {
  char fileName[] = "input_prime.txt";
  fstream fp;
  fp.open(fileName, ios::out);
  if(!fp) {
    cout << "Failed to open a file: " << fileName << endl;
    return EXIT_FAILURE;
  }
  cout << "File is opened successfully!" << endl;
  cout << "Type the number you want to examine whether it's a prime: ";
  cin >> n;
  cout << endl;
  fp << n << endl;
  fp.close();
  return 0;  
}

int prime_guage::readInput() {
  char fileName[] = "input_prime.txt";
  long long number;
  fstream fp;
  fp.open(fileName, ios::in);
  if(!fp) {
    cout << "Failed to open a file" << fileName << endl;
    return EXIT_FAILURE;
  }
  cout << "input_prime.txt File is opened successfully!" << endl;
  fp >> number;
  n = number;
  cout << number << " is going to be examined whether it's a prime." << endl;
  return 0;
}

void prime_guage::primeGuage() {
  readInput();
  factors.push_back(1);
  for(long long i=2; (i*i)<n; i++){
    if(n%i == 0) {
      primeFlag = false;
      factors.push_back(i);
      if(n/i != i) {
        factors.push_back(n/ i);
      }
    }
  }
  factors.push_back(n);
  insertionSort();
  resultOut();
}

void prime_guage::insertionSort() {
  int j;
  for(int i=1; i<factors.size(); i++) {
    j = i-1;
    if(factors[i] < factors[j]) {
      while(factors[i] < factors[j] && j>=0) {
        j--;
      }
      for(int k=i-1; k>j; k--) {
        iter_swap(factors.begin()+k+1, factors.begin()+k);
      }
    }
  }
}

int prime_guage::resultOut() {
  fstream fp;
  char fileName[] = "output_prime.txt";
  fp.open(fileName, ios::out);
  if(! fp) {
    cout << "Failed to open a file: " << fileName << endl;
    return EXIT_FAILURE;
  }
  cout << "File is opened successfully!" << endl;
  if(primeFlag==true) {
    fp << "T" << endl;
  } else {
    fp << "F" << endl;
  }
  for(int i=0; i<factors.size(); i++) {
    fp << factors[i] << " ";
  }
  fp << endl;
  cout << "The number " << n << "'s factors are recorded into the output_prime.txt!" << endl;
  fp.close();
  return 0;
}

int main() {
  prime_guage guage;
  //guage.inputInit();
  guage.primeGuage();
  return 0;
}
