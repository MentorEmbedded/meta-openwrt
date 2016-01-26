do_install_append () {
	mkdir -p ${D}/usr/bin
	pushd ${D}/usr/bin
	ln -s luajit lua
	popd 
}
