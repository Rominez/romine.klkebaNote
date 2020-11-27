#!/bin/bash

function testt(){
    arg1="$1";
    arg2="$2";
    echo $[$arg1+$arg2];
}

testt 1 2;