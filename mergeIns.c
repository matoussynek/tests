#include <stdio.h>
#include <stdlib.h>
#define THRESHOLD 3
#define ARRAY_SIZE 20

void insertion_sort(int* array, int l, int r)
{
    for (int i = l; i <= r; i++)
    {
        double tmp = array[i];
        int j = i;
        while ((j > l) && (array[j - 1] > tmp))
        {
             array[j] = array[j - 1];
             j--;
        }
        array[j] = tmp;
    }
}
void merge(int* arr, int left, int mid, int right)
{
    int i = left;
    int j = mid + 1;
    int index = left;
    int tempArray[ARRAY_SIZE];
    
    for(;index<=right;index++){
    
        if(i<= mid && (j> right || arr[i] <= arr[j]))
        {
            tempArray[index]  = arr[i];
            i++;
        }
        else
        {
            tempArray[index] = arr[j];
            j++;
        }
    }
    for(index = left;index<= right;index++){
    
        arr[index] = tempArray[index];
    }
}     
void mergesort(int* arr, int left, int right)
{
    if (left < right)
    {
        if ((right - left) <= THRESHOLD){
            insertion_sort(arr, left, right);
            printf("got here2");
        }
        else
        {
            int mid = (left + right) / 2;
            mergesort(arr, left, mid);
            mergesort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
}
int main()
{
    int array[] = {1,5,3,4,9,44,66,0,100,22,486,123,65,787,356,21,8,99,77,764};
    for(int i = 0;i<ARRAY_SIZE;i++){
        printf("%d, ",array[i]);
    }
    printf("\n");
    mergesort(array, 0, 19);
    for(int i = 0;i<ARRAY_SIZE;i++){
        printf("%d, ",array[i]);
    }
    printf("\n");
    return 0;
}
