#!/bin/bash

echo -n "Number of tests for each value of max_height: "
read -r ntests
echo -n "Starting value of max_height: "
read -r kstart
echo -n "Final value of max_height: "
read -r kend
echo -n "Output file: "
read -r output

if [ -z "$output" ]
then
	echo "Please enter valid output file name."
	exit
fi

{ for (( k=kstart; k<=kend; k++ ))
do
	echo -n "$k,"
	for (( i=1; i<=ntests; i++ ))
	do
		./bin/main_ex2_benchmark ../../resources/data/dictionary.txt ../../resources/data/correctme.txt "$k"
		if [ "$i" -lt "$ntests" ]
		then
			echo -n ","
		fi
	done
	echo
done } >> "$output"
