#!/bin/bash

echo -n "Number of tests for each value of k and field: "
read -r ntests
echo -n "Starting value of k: "
read -r kstart
echo -n "Final value of k: "
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
	for field in {1..3}
	do
		echo -n "$k,"
		echo -n "$field,"
		for (( i=1; i<=ntests; i++ ))
		do
			./bin/main_ex1_benchmark ../../resources/data/records.csv out.csv "$k" "$field"
			if [ "$i" -lt "$ntests" ]
			then
				echo -n ","
			fi
		done
		echo
	done
done } >> "$output"
