FORGE=`find / -name forge 2> /dev/null`
MOLECRAFT=`find / -name molecraft`

startup: links

release: jar

links:
	ln -sf $(MOLECRAFT)/src/common/Mole $(FORGE)/../src/common
	ln -sf $(MOLECRAFT)/src/minecraft/Mole $(FORGE)/../src/minecraft
	ln -sf $(MOLECRAFT)/resources/logo.png $(FORGE)/../src/common
	ln -sf $(MOLECRAFT)/design/mcmod.info $(FORGE)/../src/common

jar:
	cd $(FORGE)/..
	./recompile.sh
	./reobfuscate.sh
	cd reobf/minecraft
