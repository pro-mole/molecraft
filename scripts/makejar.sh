#!/bin/bash

usage()
{
	echo "makejar.sh <RELEASE> <REOBF DIR>"
}

if [[ $# < 2 ]]
then
	usage
	exit
fi

REPO_DIR="$(dirname "$0")/.."

VERSION=$1
shift

OBF_DIR=$@

TMP_DIR="$REPO_DIR/releases/$VERSION/temp"

echo $REPO_DIR
echo $VERSION
echo $OBF_DIR
echo $TMP_DIR

pwd
mkdir -pv "$TMP_DIR"

cp -rf "$OBF_DIR/Mole" "$TMP_DIR"

mkdir "$TMP_DIR/Mole/gui"
cp "$REPO_DIR/resources/gui/"*.png "$TMP_DIR/Mole/gui"

mkdir "$TMP_DIR/Mole/resources"
cp "$REPO_DIR/resources/textures/"*.png "$TMP_DIR/Mole/resources"

cp "$REPO_DIR/resources/logo.png" "$TMP_DIR/"
cp "$REPO_DIR/design/mcmod.info" "$TMP_DIR/"

cd "$TMP_DIR"
jar -cvf "../molecraft_$VERSION.jar" ./
cd ..
rm -rf temp
