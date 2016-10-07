DESCRIPTION = "ZeroMQ looks like an embeddable networking library but acts like a concurrency framework"
HOMEPAGE = "http://www.zeromq.org"
LICENSE = "LGPLv3+"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=d5311495d952062e0e4fbba39cbf3de1"

SRC_URI = "git://github.com/zeromq/libzmq.git"

SRCREV = "7f8c17b1241cfd0dfbe56a11a7d16939aa4cc49f"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE += "-DZMQ_HAVE_SOCK_CLOEXEC_EXITCODE=0"
EXTRA_OECMAKE += "-DZMQ_HAVE_SO_KEEPALIVE_EXITCODE=0"
EXTRA_OECMAKE += "-DZMQ_HAVE_TCP_KEEPCNT_EXITCODE=0"
EXTRA_OECMAKE += "-DZMQ_HAVE_TCP_KEEPIDLE_EXITCODE=0"
EXTRA_OECMAKE += "-DZMQ_HAVE_TCP_KEEPINTVL_EXITCODE=0"
EXTRA_OECMAKE += "-DZMQ_HAVE_TIPC_EXITCODE=1"
EXTRA_OECMAKE += "-DZMQ_BUILD_TESTS=OFF"

do_install_append() {
	[ -d ${D}/usr/share/zmq ] && rm -rf ${D}/usr/share
}
