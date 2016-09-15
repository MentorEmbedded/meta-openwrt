# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Lua bindings for POSIX"

DESCRIPTION = "A library binding various POSIX APIs.\
    POSIX is the IEEE Portable Operating System Interface standard.\
    luaposix is based on lposix.\
"
HOMEPAGE = "http://luaposix.github.io/luaposix/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=7dd2aad04bb7ca212e69127ba8d58f9f"
SECTION = "libs"

SRCREV = "8e4902ed81c922ed8f76a7ed85be1eaa3fd7e66d"
SRC_URI = "git://github.com/luaposix/luaposix;branch=release"
SRC_URI += "file://fix_config.patch"

S = "${WORKDIR}/git"
B = "${S}"
B = "${WORKDIR}/build"
PARALLEL_MAKE = ""

export LUA = "luajit"
export HELP2MAN = "${STAGING_DIR_NATIVE}/usr/bin/help2man"

DEPENDS = "luajit luajit-native help2man-native"
RDEPENDS_${PN} = "luajit"

EXTRA_OECONF = " LUA='${STAGING_DIR_NATIVE}/usr/bin/luajit'  LUA_INCLUDE='-I${STAGING_INCDIR}/luajit-2.0'"

inherit autotools

FILES_${PN} += "/usr/lib/lua/5.1/*"
FILES_${PN}-dbg += "/usr/lib/lua/5.1/.debug/*"

do_configure_prepend() {
	pushd ${S}
	${S}/bootstrap
	popd
}

do_install() {
	DESTDIR=${D} luaexecdir=/usr/lib/lua/5.1 oe_runmake -e install-exec
}

