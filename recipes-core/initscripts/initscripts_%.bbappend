do_install_append () {
	if test -d ${D}/var
	then
		mv ${D}/var ${D}/${localstatedir}
	fi 
}
