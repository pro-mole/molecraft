#/bin/bash
MODID=molecraft
VERSION=0.05b
#PACKAGE: JAR for .jar, ZIP for .zip; else, will not pack
PACKAGE=JAR

FORGE_DIR=
FORGE_BIN=$FORGE_DIR
FORGE_SRC=$FORGE_DIR/src
MOD_DIR=
MOD_SRC=$MOD_DIR/src
MOD_RES=$MOD_DIR/resources
RELEASE_DIR=$MOD_DIR/release

#Create link in Minecraft src folder from mod src folder
ln $FORGE_SRC/

#Recompile & Reobfuscate
$FORGE_BIN/recompile.sh
$FORGE_BIN/reobfuscate.sh

#Copy resources(textures, mcmod)
cd $FORGE_DIR/reobf/minecraft
cp $MOD_RES/mcmod.info ./
cp -r $MOD_RES/textures ./mods/$MODID

#Pack mod
if [ $PACKAGE -eq JAR ]
then
	jar -cf $RELEASE_DIR/$MODID-$VERSION.jar ./
elif [ $PACKAGE -eq ZIP ]
then
	zip $RELEASE_DIR/$MODID-$VERSION.zip ./
else
	mkdir $RELEASE_DIR/$MODID-$VERSION
	cp -t $RELEASE_DIR/$MODID-$VERSION -r *
fi

#Remove link in Minecraft src folder