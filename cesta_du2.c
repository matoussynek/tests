#include <stdio.h>
#include <stdlib.h>


/*
 * Function for obtaining string length
 * const char *input = string we want to know the length of
 * Returns the length as Int32
 */
int length(const char *input){

    int len;
    for (len =0; input[len] != '\0';len++);
    return len;
}

/*
 * Funciton that returns position of the fisrt occurance of a substring in the string
 * char *str 
 */
int findPos(const char *str,const char *target,const int pos){
    int i,j,res;
    i = pos;
    while(str[i]!='\0'){
        if(str[i] == target[0]){
            res = i;
            i++;
            j=1;
            while(target[j]!='\0'){
                if (str[i] == target[j]){
                    i++;
                    j++;
                    continue;
                }
                else{
                    break;
                }
            }
            if (target[j] == '\0'){
                return(res);
            }
            else {
            
                i = res;
            }
        }
        i++;
    }
    return(-1);
}
int delete(const char *text,const char *start,const char* end){
    int startLength = length(start);
    int s = findPos(text,start,0);
    if(s==-1) return -1;
    int firstAfterStart = s+ startLength;
    int e = findPos(text,end,firstAfterStart);
    if(e==-1) return -2;
    int i =0;
    printf("The modified string is: ");
    while(text[i]!='\0'){
        if(i<s || i>=e+length(end)){
            printf("%c",text[i]);
        }
        i++;
    }
    printf("\n");
    return (e-s+1);    
}

int main(){
    char *text = {"ahoj, jak se mas? ja, diky docela dobre."};
    char *start = {","};
    char *end  = {","};
    int retVal = delete(text,start,end);
    switch(retVal){
        case -1:
            printf("Couldn't find start substring.\n");
            break;
        case -2:
            printf("Couldn't find end substring.\n");
            break;
        default:
            printf("Number of deleted characters: %d\n",retVal);
            break;
    }
    return 0;
}
