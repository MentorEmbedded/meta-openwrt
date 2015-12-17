DESCRIPTION = "Lua LibUV async io bindings for lua"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/mkschreder/luv.git;protocol=git;branch=master"
SRCREV = "4602b7a57576cdd3f6e05fabc79d2a422f1d49e3"
SRC_URI += "file://fix_luajit.patch"

S = "${WORKDIR}/git"
B = "${S}"

PR="r1"

inherit cmake

DEPENDS = "luajit libuv"
RDEPENDS_${PN} = "luajit libuv"

# FIXME: please things as commented below instead of patch
#EXTRA_OEMAKE = " WITH_LUA_ENGINE=LuaJIT " 
#EXTRA_OECMAKE = " -DWITH_LUA_ENGINE=LuaJIT " 

FILES_${PN} += "/usr/lib/lua/5.1/*.so"
FILES_${PN}-dbg += "/usr/lib/lua/5.1/.debug/*.so"

do_install_append () {
	mkdir -p ${D}/usr/lib/lua/
	#install -m 722 ${B}/build_dir/ubus-scriptd ${D}/sbin/ubus-scriptd
}

