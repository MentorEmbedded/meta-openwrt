DESCRIPTION = "OpenWrt RPC daemon"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/ubus"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://ubusd.c;beginline=1;endline=12;md5=1b6a7aecd35bdd25de35da967668485d"

SRC_URI = "git://github.com/mkschreder/juci-ubus.git;protocol=git;branch=master"
SRC_URI += "file://luajit.patch"
		   
SRCREV = "f847413259c01f8ff9308b3048b9aac277ad95a3"
S = "${WORKDIR}/git"

inherit cmake

PR="r1"

DEPENDS = "libubox luajit"
EXTRA_OECMAKE = "-DLUAPATH=/usr/lib/lua/5.1"
PACKAGES += "libubus"
RDEPENDS_${PN} += "libubox libubus"
RDEPENDS_libubus += "libubox"

FILES_${PN} = "/usr/lib/lua/5.1 /bin /sbin"
FILES_${PN}-dev = "/usr/include /usr/lib/lib*.so"
FILES_libubus = "${base_libdir}"
FILES_${PN}-dbg += "/usr/lib/lua/5.1/.debug"


do_install_append () {
        mkdir -p ${D}/lib
        
        install ${D}/usr/lib/libubus.so ${D}/${base_libdir}
	mv ${D}/usr/sbin ${D}/sbin
	mv ${D}/usr/bin ${D}/bin
	install ${S}/lua/test.lua ${D}/usr/lib/lua/5.1
	install ${S}/lua/test_client.lua ${D}/usr/lib/lua/5.1
}
