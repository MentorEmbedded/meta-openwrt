DESCRIPTION = "Unified Configuration Interface"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/uci"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://libuci.c;beginline=1;endline=13;md5=0ee862ed12171ee619c8c2eb7eff77f2"

SRC_URI = "git://nbd.name/uci.git;protocol=git;branch=master"
SRC_URI += "file://uci.sh"
		   
SRCREV = "556215152a216c179fe2ca7db9b1de7036ceda60"
S = "${WORKDIR}/git"


inherit cmake

PR="r1"
DEPENDS = "libubox lua5.1"

EXTRA_OECMAKE = "-DLUAPATH=/usr/lib/lua/5.1"
RDEPENDS_${PN} += "libuci"

PACKAGES += "libuci"
FILES_${PN} = "/usr/lib/lua/5.1 /usr/bin /sbin "
FILES_${PN}-dev = "/usr/include /usr/lib/libuci.so"
FILES_libuci = "${base_libdir}"
FILES_${PN}-dbg += "/usr/lib/lua/5.1/.debug"


do_install_append () {
        mkdir -p ${D}/lib ${D}/lib/config ${D}/sbin
        install ${D}/usr/lib/libuci.so ${D}/${base_libdir}
        install ${WORKDIR}/uci.sh ${D}/${base_libdir}/config
        install ${D}/usr/bin/uci ${D}/sbin
}
