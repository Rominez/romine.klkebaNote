#include <iostream>
using namespace std;

int main(){
    string a = "123";
    for(int i=0;i<sizeof(a);i++){
        cout<<i<<' '<<a[i]<<'\n';
    }
}