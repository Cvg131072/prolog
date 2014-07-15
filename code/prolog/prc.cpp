///////////////////////////////////////////////////////////////////////////////////
//                       Copyright (C) 2004 Robert P. Wolf                       //
//                                                                               //
// Permission is hereby granted, free of charge, to any person obtaining a copy  //
// of this software and associated documentation files (the "Software"), to deal //
// in the Software without restriction, including without limitation the rights  //
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell     //
// copies of the Software, and to permit persons to whom the Software is         //
// furnished to do so, subject to the following conditions:                      //
//                                                                               //
// The above copyright notice and this permission notice shall be included in    //
// all copies or substantial portions of the Software.                           //
//                                                                               //
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR    //
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,      //
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE   //
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER        //
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, //
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN     //
// THE SOFTWARE.                                                                 //
///////////////////////////////////////////////////////////////////////////////////

#include "prolog.h"

#include <string.h>

#ifdef INTERNAL_RESOURCES
#include "prolog_neural.h"
#include "prolog_conductor.h"
#include "prolog_midi.h"
#include "prolog_xml.h"
#include "prolog_distribution.h"
#include "prolog_control.h"

#ifdef WINDOWS_OPERATING_SYSTEM
#include "prolog_irrklang.h"
#include "resource.h"
class resource_loader_class : public PrologResourceLoader {
public:
	char * load (char * name) {
		HRSRC resource = NULL;
		if (strcmp (name, "studio") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (STUDIO_PRC), RT_RCDATA);
		if (strcmp (name, "conductor") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (CONDUCTOR_PRC), RT_RCDATA);
		if (strcmp (name, "midi") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (MIDI_PRC), RT_RCDATA);
		if (strcmp (name, "http") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (HTTP_PRC), RT_RCDATA);
		if (strcmp (name, "store") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (STORE_PRC), RT_RCDATA);
		if (strcmp (name, "f1") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (F1_PRC), RT_RCDATA);
		if (strcmp (name, "help") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (HELP_PRC), RT_RCDATA);
		if (strcmp (name, "record") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (RECORD_PRC), RT_RCDATA);
		if (strcmp (name, "neural") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (NEURAL_PRC), RT_RCDATA);
		if (strcmp (name, "keyboard") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (KEYBOARD_PRC), RT_RCDATA);
		if (strcmp (name, "sql") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (SQL_PRC), RT_RCDATA);
		if (strcmp (name, "test") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (TEST_PRC), RT_RCDATA);
		if (strcmp (name, "xml") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (XML_PRC), RT_RCDATA);
		if (strcmp (name, "distribution") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (DISTRIBUTION_PRC), RT_RCDATA);
		if (strcmp (name, "control") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (CONTROL_PRC), RT_RCDATA);
		if (strcmp (name, "irrklang") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (IRRKLANG_PRC), RT_RCDATA);
		if (strcmp (name, "studio.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (STUDIO_PRC), RT_RCDATA);
		if (strcmp (name, "conductor.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (CONDUCTOR_PRC), RT_RCDATA);
		if (strcmp (name, "midi.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (MIDI_PRC), RT_RCDATA);
		if (strcmp (name, "http.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (HTTP_PRC), RT_RCDATA);
		if (strcmp (name, "store.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (STORE_PRC), RT_RCDATA);
		if (strcmp (name, "f1.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (F1_PRC), RT_RCDATA);
		if (strcmp (name, "help.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (HELP_PRC), RT_RCDATA);
		if (strcmp (name, "record.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (RECORD_PRC), RT_RCDATA);
		if (strcmp (name, "neural.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (NEURAL_PRC), RT_RCDATA);
		if (strcmp (name, "keyboard.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (KEYBOARD_PRC), RT_RCDATA);
		if (strcmp (name, "sql.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (SQL_PRC), RT_RCDATA);
		if (strcmp (name, "test.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (TEST_PRC), RT_RCDATA);
		if (strcmp (name, "xml.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (XML_PRC), RT_RCDATA);
		if (strcmp (name, "distribution.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (DISTRIBUTION_PRC), RT_RCDATA);
		if (strcmp (name, "control.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (CONTROL_PRC), RT_RCDATA);
		if (strcmp (name, "irrklang.prc") == 0) resource = FindResource (NULL, MAKEINTRESOURCE (IRRKLANG_PRC), RT_RCDATA);
		if (! resource) return NULL;
		HGLOBAL loader = LoadResource (NULL, resource);
		if (! loader) return NULL;
		return (char *) LockResource (loader);
	}
} resource_loader;
#endif

#ifdef LINUX_OPERATING_SYSTEM
#include "prolog_http.h"
extern char resource_0 [];
extern char resource_1 [];
extern char resource_2 [];
extern char resource_3 [];
extern char resource_4 [];
extern char resource_5 [];
extern char resource_6 [];
extern char resource_7 [];
extern char resource_8 [];
extern char resource_9 [];
extern char resource_10 [];
extern char resource_11 [];
extern char resource_12 [];
extern char resource_13 [];
extern char resource_14 [];
class resource_loader_class : public PrologResourceLoader {
public:
	char * load (char * name) {
		char * ret = NULL;
		if (strcmp (name, "studio") == 0) ret = resource_0;
		if (strcmp (name, "conductor") == 0) ret = resource_1;
		if (strcmp (name, "midi") == 0) ret = resource_2;
		if (strcmp (name, "http") == 0) ret = resource_3;
		if (strcmp (name, "store") == 0) ret = resource_4;
		if (strcmp (name, "f1") == 0) ret = resource_5;
		if (strcmp (name, "help") == 0) ret = resource_6;
		if (strcmp (name, "record") == 0) ret = resource_7;
		if (strcmp (name, "neural") == 0) ret = resource_8;
		if (strcmp (name, "keyboard") == 0) ret = resource_9;
		if (strcmp (name, "sql") == 0) ret = resource_10;
		if (strcmp (name, "test") == 0) ret = resource_11;
		if (strcmp (name, "xml") == 0) ret = resource_12;
		if (strcmp (name, "distribution") == 0) ret = resource_13;
		if (strcmp (name, "control") == 0) ret = resource_14;
		if (strcmp (name, "studio.prc") == 0) ret = resource_0;
		if (strcmp (name, "conductor.prc") == 0) ret = resource_1;
		if (strcmp (name, "midi.prc") == 0) ret = resource_2;
		if (strcmp (name, "http.prc") == 0) ret = resource_3;
		if (strcmp (name, "store.prc") == 0) ret = resource_4;
		if (strcmp (name, "f1.prc") == 0) ret = resource_5;
		if (strcmp (name, "help.prc") == 0) ret = resource_6;
		if (strcmp (name, "record.prc") == 0) ret = resource_7;
		if (strcmp (name, "neural.prc") == 0) ret = resource_8;
		if (strcmp (name, "keyboard.prc") == 0) ret = resource_9;
		if (strcmp (name, "sql.prc") == 0) ret = resource_10;
		if (strcmp (name, "test.prc") == 0) ret = resource_11;
		if (strcmp (name, "xml.prc") == 0) ret = resource_12;
		if (strcmp (name, "distribution.prc") == 0) ret = resource_13;
		if (strcmp (name, "control.prc") == 0) ret = resource_14;
		return ret;
	}
} resource_loader;
#endif

class service_class_loader_class : public PrologServiceClassLoader {
public:
	PrologServiceClass * load (char * name) {
		if (strcmp (name, "prolog.conductor") == 0) return new PrologConductorServiceClass ();
		if (strcmp (name, "prolog.midi") == 0) return new PrologMidiServiceClass ();
#ifdef LINUX_OPERATING_SYSTEM
		if (strcmp (name, "prolog.http") == 0) return new PrologHttpServiceClass ();
#endif
		if (strcmp (name, "prolog.neural") == 0) return new PrologNeuralServiceClass ();
		if (strcmp (name, "prolog.xml") == 0) return new PrologXMLServiceClass ();
		if (strcmp (name, "prolog.distribution") == 0) return new PrologDistributionServiceClass ();
		if (strcmp (name, "prolog.control") == 0) return new PrologControlServiceClass ();
#ifdef WINDOWS_OPERATING_SYSTEM
		if (strcmp (name, "prolog.irrklang") == 0) return new PrologIrrKlangServiceClass ();
#endif
		return NULL;
	}
} service_class_loader;

#endif

#ifdef WINDOWS_OPERATING_SYSTEM
#include "prolog_windows_console.h"
#endif

#ifdef LINUX_OPERATING_SYSTEM
#include "prolog_linux_console.h"
#endif

int main (int args, char * argv []) {
	PrologRoot * root = new PrologRoot ();
	root -> get_search_directories_from_environment ("PRC_MODULE_SEARCH_PATHS");
	#ifdef INTERNAL_RESOURCES
	root -> setResourceLoader (& resource_loader);
	root -> setServiceClassLoader (& service_class_loader);
	#endif
	root -> set_uap32_captions ();
	PROLOG_STRING name;
	strcpy (name, "");
	for (int ind = 1; ind < args; ind++) {
		if (strlen (name) < 1) strcpy (name, argv [ind]);
		else root -> addArg (argv [ind]);
	}

	#ifdef LINUX_OPERATING_SYSTEM
	PrologCommand * console = new PrologLinuxConsole ();
	#endif
	#ifdef WINDOWS_OPERATING_SYSTEM
	PrologCommand * console = new PrologWindowsConsole ();
	#endif

	root -> insertCommander (console);
	if (strlen (name) == 0) {root -> auto_atoms = true; root -> resolution ();}
	else {
		if (strstr (name, ".prc") == NULL && strstr (name, ".prb") == NULL) strcat (name, ".prc");
		root -> resolution (name);
	}

	delete root;
	delete console;
//	if (object_left ())
		drop_object_counter ();

	return 0;
}

