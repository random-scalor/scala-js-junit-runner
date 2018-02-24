#!/bin/bash
set -e -u

#
# produce cross project symlinks
#

base=$(cd "${BASH_SOURCE%/*}/.." && pwd)

cross211="$base/cross/2.11"
cross212="$base/cross/2.12"
cross213="$base/cross/2.13"

echo "base       $base"

echo "cross211       $cross211"
echo "cross212       $cross212"
echo "cross213       $cross213"

make_link() {
    ln -v -f -r -s -T "$1" "$2"
}

# sources
make_link "$base/src" "$cross211/src"
make_link "$base/src" "$cross212/src"
make_link "$base/src" "$cross213/src"

# test-tool
make_link "$base/test-tool" "$cross211/test-tool"
make_link "$base/test-tool" "$cross212/test-tool"
make_link "$base/test-tool" "$cross213/test-tool"

# test-repo
make_link "$base/test-repo" "$cross211/test-repo"
make_link "$base/test-repo" "$cross212/test-repo"
make_link "$base/test-repo" "$cross213/test-repo"
