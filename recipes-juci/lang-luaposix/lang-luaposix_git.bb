DESCRIPTION = "Scripted ubus object management daemon"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;;md5=0dedf8962dbce44c66c3312ccee647d0"

SRC_URI = "git://github.com/luaposix/luaposix.git;protocol=git;branch=master"
SRC_URI += "file://fix_config.patch"
SRCREV = "27d6a65865824867f466b3fbfba7802d3cd83fbf"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
PARALLEL_MAKE = ""

PR="r1"
export HELP2MAN = "${STAGING_DIR_NATIVE}/usr/bin/help2man"

inherit autotools

DEPENDS = "luajit luajit-native help2man-native"
RDEPENDS_${PN} = "luajit"

EXTRA_OECONF = " LUA='${STAGING_DIR_NATIVE}/usr/bin/luajit'  LUA_INCLUDE='-I${STAGING_INCDIR}/luajit-2.0'"

do_configure_prepend () {
	pushd ${S}
	./bootstrap
	popd
}

FILES_${PN} += "/usr/lib/lua/5.1"
FILES_${PN}-dbg += "/usr/lib/lua/5.1/.debug"

do_install() {
	#luajit
	mkdir -p ${D}/usr/lib/lua/5.1
	install -m 622 ${B}/ext/posix/.libs/posix.so ${D}/usr/lib/lua/5.1
}

