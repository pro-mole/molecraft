#/bin/bash
MODID=molecraft
VERSION=0.05b
#PACKAGE: JAR for .jar, ZIP for .zip; else, will not pack
PACKAGE=JAR

FORGE_DIR=/Users/guedesav/Devel/forge/mcp
FORGE_BIN=$FORGE_DIR
FORGE_SRC=$FORGE_DIR/src
MOD_DIR=/Users/guedesav/Devel/Minecraft/Molecraft/bugfarm
MOD_SRC=$MOD_DIR/src
MOD_RES=$MOD_DIR
RELEASE_DIR=$MOD_DIR/release

cd $FORGE_BIN

#Create link in Minecraft src folder from mod src folder
ln -s $MOD_SRC/common $FORGE_SRC/minecraft/net/common

#Recompile & Reobfuscate
$FORGE_BIN/recompile.sh
$FORGE_BIN/reobfuscate.sh

#Copy resources(textures, mcmod)
echo "Copying resources..."
cd $FORGE_DIR/reobf/minecraft
cp $MOD_DIR/modinfo/mcmod.info ./
cp -rf $MOD_RES/textures ./

#Pack mod
if [ $PACKAGE=JAR ]
then
	echo "Packing JAR..."
	jar -cf $RELEASE_DIR/$MODID-$VERSION.jar ./
elif [ $PACKAGE=ZIP ]
then
	echo "Packing ZIP..."
	zip $RELEASE_DIR/$MODID-$VERSION.zip ./
else
	echo "Packing release..."
	mkdir $RELEASE_DIR/$MODID-$VERSION
	cp -t $RELEASE_DIR/$MODID-$VERSION -r *
fi

#Remove link in Minecraft src folder
rm $FORGE_SRC/minecraft/net/common
