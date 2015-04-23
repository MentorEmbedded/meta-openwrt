DESCRIPTION = "Basic utility library"
HOMEPAGE = "http://wiki.openwrt.org/doc/uci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../Makefile;beginline=1;endline=5;md5=3ce05a39dbd458b46a410c5a9f266107"

SRC_URI = "git://git.openwrt.org/project/luci2/ui.git;protocol=git;branch=master"
		   
SRCREV = "e452ca693af5278ff2ddc69b6f8ed0f346c98fb1"
S = "${WORKDIR}/git/luci2/src/"
S_LUCI2 = "${WORKDIR}/git/luci2/"

inherit cmake

PR="r1"

DEPENDS = "rpcd uhttpd2 libubox ubus"
FILES_${PN} += "/usr /www"
FILES_${PN}-dbg += "/usr/libexec/.debug /usr/lib/rpcd/.debug"

do_install_append () {
	mkdir -p ${D}/www ${D}/usr/share/rpcd ${D}/usr/lib/rpcd ${D}/www/cgi-bin
	cp -a ${S_LUCI2}/htdocs/* ${D}/www/
	cp -a ${S_LUCI2}/share/* ${D}/usr/share/rpcd/
	mv ${D}/usr/lib/{luci2.so,bwmon.so}  ${D}/usr/lib/rpcd/
	mv ${D}/usr/sbin ${D}/usr/libexec
	ln -sf /usr/libexec/luci2-io ${D}/www/cgi-bin/luci-upload
        ln -sf /usr/libexec/luci2-io ${D}/www/cgi-bin/luci-backup

}
