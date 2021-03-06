/* Copyright 2013 David Axmark

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

#include "config_platform.h"

#include <helpers/helpers.h>
#include <bluetooth/bt_errors.h>
#include <net/net_errors.h>
#include "base_errors.h"
#include "wce_errors.h"

#define ERROR_SETS(m) m(HELPERS) m(BASE) m(WCE) m(BT) m(NET)

#include <helpers/errors_inline.h>
