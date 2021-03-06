///////////////////////////////////////////////////////////////////////////////////
//                       Copyright (C) 2016 Robert P. Wolf                       //
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

package fxg;

import prolog . *;
import prolog . geometry . *;

import java . io . FileWriter;
import java . util . ArrayList;

import javafx . scene . paint . Color;

class viewport_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean main;
	public boolean code (PrologElement parameters, PrologResolution resolution) {
		PrologElement name = null;
		PrologElement atom = null;
		double locations [] = {100.0, 100.0, 160.0, 90.0};
		int location_index = 0;
		while (parameters . isPair ()) {
			PrologElement el = parameters . getLeft ();
			if (el . isAtom ()) atom = el;
			if (el . isVar ()) atom = el;
			if (el . isText ()) name = el;
			if (el . isNumber () && location_index < 4) locations [location_index++] = el . getNumber ();
			parameters = parameters . getRight ();
		}
		if (atom == null) return false;
		if (atom . isVar ()) atom . setAtom (new PrologAtom ());
		if (! atom . isAtom ()) return false;
		if (atom . getAtom () . getMachine () != null) return false;
		Rect location = new Rect (locations [0], locations [1], locations [2], locations [3]);
		fxg . viewports = new Viewport (fxg, atom . getAtom (),
				fxg . default_viewport_foreground, fxg . default_viewport_background,
				name != null ? name . getText () : atom . getAtom () . name (),
				location, main, fxg . viewports);
		if (! atom . getAtom () . setMachine (fxg . viewports)) return false;
		return true;
	}
	public viewport_class (PrologFXGStudio fxg, boolean main) {this . fxg = fxg; this . main = main;}
}

class erase_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {fxg . erase (); return true;}
	public erase_class (PrologFXGStudio fxg) {this . fxg = fxg;}
}

class clean_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {fxg . clean = true; return true;}
	public clean_class (PrologFXGStudio fxg) {this . fxg = fxg;}
}

class is_clean_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {return fxg . clean;}
	public is_clean_class (PrologFXGStudio fxg) {this . fxg = fxg;}
}

class save_board_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {
		if (! parameters . isPair ()) return false; parameters = parameters . getLeft (); if (! parameters . isText ()) return false;
		fxg . save (parameters . getText ());
		return true;
	}
	public save_board_class (PrologFXGStudio fxg) {this . fxg = fxg;}
}

class repaint_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {fxg . repaint (); return true;}
	public repaint_class (PrologFXGStudio fxg) {this . fxg = fxg;}
}

class rectangle_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {
		if (! parameters . isPair ()) return false;
		PrologElement atom = parameters . getLeft ();
		if (atom . isVar ()) atom . setAtom (new PrologAtom ());
		if (! atom . isAtom ()) return false;
		if (atom . getAtom () . getMachine () != null) return false;
		Token machine = new RectangleToken (fxg, atom . getAtom (), fxg . default_rectangle_foreground, fxg . default_rectangle_background, null);
		if (! atom . getAtom () . setMachine (machine)) return false;
		fxg . insert_token (machine);
		fxg . clean = false;
		return true;
	}
	public rectangle_class (PrologFXGStudio fxg) {this . fxg = fxg;}
}

class circle_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {
		if (! parameters . isPair ()) return false;
		PrologElement atom = parameters . getLeft ();
		if (atom . isVar ()) atom . setAtom (new PrologAtom ());
		if (! atom . isAtom ()) return false;
		if (atom . getAtom () . getMachine () != null) return false;
		Token machine = new CircleToken (fxg, atom . getAtom (), fxg . default_rectangle_foreground, fxg . default_rectangle_background, null);
		if (! atom . getAtom () . setMachine (machine)) return false;
		fxg . insert_token (machine);
		fxg . clean = false;
		return true;
	}
	public circle_class (PrologFXGStudio fxg) {this . fxg = fxg;}
}

class grid_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {
		if (! parameters . isPair ()) return false;
		PrologElement atom = parameters . getLeft ();
		if (atom . isVar ()) atom . setAtom (new PrologAtom ());
		if (! atom . isAtom ()) return false;
		if (atom . getAtom () . getMachine () != null) return false;
		Token machine = new GridToken (fxg, atom . getAtom (), fxg . default_grid_foreground, fxg . default_grid_background, null);
		if (! atom . getAtom () . setMachine (machine)) return false;
		fxg . insert_token (machine);
		fxg . clean = false;
		return true;
	}
	public grid_class (PrologFXGStudio fxg) {this . fxg = fxg;}
}

class text_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {
		if (! parameters . isPair ()) return false;
		PrologElement atom = parameters . getLeft ();
		if (atom . isVar ()) atom . setAtom (new PrologAtom ());
		if (! atom . isAtom ()) return false;
		if (atom . getAtom () . getMachine () != null) return false;
		parameters = parameters . getRight ();
		String text = atom . getAtom () . name ();
		double font_height = 16.0;
		while (parameters . isPair ()) {
			PrologElement el = parameters . getLeft ();
			if (el . isNumber ()) font_height = el . getNumber ();
			if (el . isText ()) text = el . getText ();
			parameters = parameters . getRight ();
		}
		Token machine = new TextToken (fxg, atom . getAtom (), text, font_height, fxg . default_text_foreground, fxg . default_text_background, null);
		if (! atom . getAtom () . setMachine (machine)) return false;
		fxg . insert_token (machine);
		fxg . clean = false;
		return true;
	}
	public text_class (PrologFXGStudio fxg) {this . fxg = fxg;}
}

class picture_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {
		PrologElement atom = null;
		ArrayList <String> pictures = new ArrayList <String> ();
		while (parameters . isPair ()) {
			PrologElement el = parameters . getLeft ();
			if (el . isVar ()) atom = el;
			if (el . isAtom ()) atom = el;
			if (el . isText ()) pictures . add (el . getText ());
			parameters = parameters . getRight ();
		}
		if (atom == null) return false;
		if (atom . isVar ()) atom . setAtom (new PrologAtom ());
		if (! atom . isAtom ()) return false;
		if (atom . getAtom () . getMachine () != null) return false;
		Token machine = new PictureToken (fxg, atom . getAtom (), pictures, fxg . default_picture_foreground, fxg . default_picture_background, null);
		if (! atom . getAtom () . setMachine (machine)) return false;
		fxg . insert_token (machine);
		fxg . clean = false;
		return true;
	}
	public picture_class (PrologFXGStudio fxg) {this . fxg = fxg;}
}

class dice_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {
		PrologElement atom = null, sides = null, shift = null, multiplier = null;
		while (parameters . isPair ()) {
			PrologElement el = parameters . getLeft ();
			if (el . isVar ()) atom = el;
			if (el . isAtom ()) atom = el;
			if (el . isInteger ()) {if (sides == null) sides = el; else if (shift == null) shift = el; else multiplier = el;}
			parameters = parameters . getRight ();
		}
		if (atom == null) return false;
		if (atom . isVar ()) atom . setAtom (new PrologAtom ());
		if (! atom . isAtom ()) return false;
		if (atom . getAtom () . getMachine () != null) return false;
		Token machine = new DiceToken (fxg, atom . getAtom (),
																			sides != null ? sides . getInteger () : 0,
																			shift != null ? shift . getInteger () : 1,
																			multiplier != null ? multiplier . getInteger () : 1, null);
		if (! atom . getAtom () . setMachine (machine)) return false;
		fxg . insert_token (machine);
		fxg . clean = false;
		return true;
	}
	public dice_class (PrologFXGStudio fxg) {this . fxg = fxg;}
}

class deck_class extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {
		PrologElement atom = null, text = null;
		while (parameters . isPair ()) {
			PrologElement el = parameters . getLeft ();
			if (el . isVar ()) atom = el;
			if (el . isAtom ()) atom = el;
			if (el . isText ()) text = el;
			parameters = parameters . getRight ();
		}
		if (atom == null) return false;
		if (atom . isVar ()) atom . setAtom (new PrologAtom ());
		if (! atom . isAtom ()) return false;
		if (atom . getAtom () . getMachine () != null) return false;
		Token machine = new DeckToken (fxg, atom . getAtom (), text == null ? atom . getAtom () . name () : text . getText (), null);
		if (! atom . getAtom () . setMachine (machine)) return false;
		fxg . insert_token (machine);
		fxg . clean = false;
		return true;
	}
	public deck_class (PrologFXGStudio fxg) {this . fxg = fxg;}
}

class DefaultBackgroundClass extends PrologNativeCode {
	public PrologFXGStudio fxg;
	public boolean code (PrologElement parameters, PrologResolution resolution) {
		PrologElement c [] = new PrologElement [4];
		int count = 0;
		PrologElement var = null;
		while (parameters . isPair ()) {
			PrologElement el = parameters . getLeft ();
			if (el . isVar ()) var = el;
			if (el . isNumber () && count < 4) c [count++] = el;
			parameters = parameters . getRight ();
		}
		if (parameters . isVar ()) var = parameters;
		if (var != null) {
			var . setPair (); var . getLeft () . setDouble (fxg . default_viewport_background . red); var = var . getRight ();
			var . setPair (); var . getLeft () . setDouble (fxg . default_viewport_background . green); var = var . getRight ();
			var . setPair (); var . getLeft () . setDouble (fxg . default_viewport_background . blue); var = var . getRight ();
			var . setPair (); var . getLeft () . setDouble (fxg . default_viewport_background . alpha); return true;
		}
		if (count == 3) {
			fxg . default_viewport_background = new Colour (c [0] . getNumber (), c [1] . getNumber (), c [2] . getNumber ());
			return true;
		}
		if (count == 4) {
			fxg . default_viewport_background = new Colour (c [0] . getNumber (), c [1] . getNumber (), c [2] . getNumber (), c [3] . getNumber ());
			return true;
		}
		return false;
	}
	public DefaultBackgroundClass (PrologFXGStudio fxg) {this . fxg = fxg;}
}

public class PrologFXGStudio extends PrologServiceClass {
	public PrologRoot root;
	public PrologDirectory directory;
	public PrologAtom location_atom, size_atom, position_atom, scaling_atom, repaint_atom, rotation_atom, rounding_atom;
	public PrologAtom side_atom, sides_atom;
	public PrologAtom foreground_atom, background_atom;
	public PrologAtom indexing_atom, indexed_atom, select_atom, deselect_atom, selected_atom;
	public PrologAtom lock_atom, unlock_atom, locked_atom;
	public PrologAtom roll_atom, text_atom;
	public boolean clean = true;
	public Token viewports;
	public Token tokens;
	public DeckToken currentDeck = null;
	public Colour default_viewport_foreground = new Colour (0.0, 0.0, 0.0), default_viewport_background = new Colour (0.0, 0.0, 1.0);
	public Color vbgcc () {return Color . color (default_viewport_background . red, default_viewport_background . green,
												default_viewport_background . blue, default_viewport_background . alpha);}
	public Colour default_rectangle_foreground = new Colour (1.0, 1.0, 0.0), default_rectangle_background = new Colour (0.0, 0.0, 0.0);
	public Colour default_grid_foreground = new Colour (1.0, 1.0, 1.0), default_grid_background = new Colour (0.0, 0.0, 0.0, 0.0);
	public Colour default_text_foreground = new Colour (1.0, 1.0, 0.0), default_text_background = new Colour (1.0, 1.0, 0.0);
	public Colour default_picture_foreground = new Colour (1.0, 1.0, 0.0), default_picture_background = new Colour (1.0, 1.0, 0.0);
	public Colour default_dice_foreground = new Colour (0.0, 0.0, 0.0), default_dice_background = new Colour (1.0, 1.0, 1.0);
	public Colour default_tetrahedron_foreground = new Colour (0.0, 0.0, 0.0), default_tetrahedron_background = new Colour (1.0, 0.0, 0.0);
	public Colour default_cube_foreground = new Colour (0.0, 0.0, 0.0), default_cube_background = new Colour (0.0, 0.0, 1.0);
	public Colour default_octahedron_foreground = new Colour (0.0, 0.0, 0.0), default_octahedron_background = new Colour (0.0, 1.0, 0.0);
	public Colour default_deltahedron_foreground = new Colour (0.0, 0.0, 0.0), default_deltahedron_background = new Colour (1.0, 1.0, 0.0);
	public Colour default_deltahedron_10_foreground = new Colour (0.0, 0.0, 0.0), default_deltahedron_10_background = new Colour (1.0, 0.5, 0.0);
	public Colour default_dodecahedron_foreground = new Colour (0.0, 0.0, 0.0), default_dodecahedron_background = new Colour (0.5, 1.0, 0.0);
	public Colour default_icosahedron_foreground = new Colour (1.0, 1.0, 0.0), default_icosahedron_background = new Colour (0.25, 0.0, 1.0);
	public Colour default_deck_foreground = new Colour (0.0, 0.0, 0.0), default_deck_background = new Colour (1.0, 1.0, 0.0);
	public Colour dice_foreground (int sides, int multiplier) {
		switch (sides) {
		case 0: return default_dice_foreground;
		case 4: return default_tetrahedron_foreground;
		case 6: return default_cube_foreground;
		case 8: return default_octahedron_foreground;
		case 10: return multiplier == 10 ? default_deltahedron_10_foreground : default_deltahedron_foreground;
		case 12: return default_dodecahedron_foreground;
		case 20: return default_icosahedron_foreground;
		default: break;
		}
		return default_dice_foreground;
	}
	public Colour dice_background (int sides, int multiplier) {
		switch (sides) {
		case 0: return default_dice_background;
		case 4: return default_tetrahedron_background;
		case 6: return default_cube_background;
		case 8: return default_octahedron_background;
		case 10: return multiplier == 10 ? default_deltahedron_10_background : default_deltahedron_background;
		case 12: return default_dodecahedron_background;
		case 20: return default_icosahedron_background;
		default: break;
		}
		return default_dice_background;
	}
	public void repaint () {
		Token v = viewports;
		while (v != null) {v . repaint (); v = v . next;}
	}
	public void draw (javafx . scene . canvas . GraphicsContext gc, Viewport v) {
		Token t = tokens;
		while (t != null) {t . token_draw (gc, v); t = t . next;}
	}
	public Token insert_token (Token token) {
		DeckToken deck = findSelectedDeck ();
		if (deck != null) {token . next = deck . tokens; return deck . tokens = token;}
		if (tokens == null) return tokens = token;
		Token tk = tokens;
		while (tk . next != null) tk = tk . next;
		return tk . next = token;
	}
	public void remove_token (Token token) {
		if (tokens == token) {tokens = tokens . next; return;}
		Token t = tokens;
		while (t . next != null) {
			if (t . next == token) {t . next = token . next; return;}
			t = t . next;
		}
	}
	public void selected_tokens_to_front () {
		Token stack = null;
		Token t;
		while (tokens != null && tokens . selected) {t = tokens; tokens = t . next; t . next = stack; stack = t;}
		t = tokens;
		while (t != null && t . next != null) {
			if (t . next . selected) {Token tt = t . next; t . next = tt . next; tt . next = stack; stack = tt;}
			t = t . next;
		}
		if (tokens == null) {tokens = stack; return;}
		t = tokens;
		while (t . next != null) t = t . next;
		t . next = stack;
	}
	public void remove_viewport (Token viewport) {
		if (viewports == null) return;
		if (viewports == viewport) {viewports = viewports . next; return;}
		Token v = viewports;
		while (v . next != null) {if (v . next == viewport) {v . next = v . next . next; return;} v = v . next;}
	}
	public void save (String file_name) {
		try {
			FileWriter tc = new FileWriter (root . ccd (file_name));
			tc . write ("[auto_atoms]\n\n");
			tc . write ("[Erase]\n\n");
			if (! default_viewport_background . eq (new Colour (0.0, 0.0, 1.0)))
				tc . write ("[DefaultBackground " + default_viewport_background . red + " " + default_viewport_background . green + " "
				+ default_viewport_background . blue + " " + default_viewport_background . alpha + "]\n\n");
			Token token = tokens;
			while (token != null) {token . save (tc); token = token . next;}
			if (viewports != null) viewports . save_stack (tc);
			tc . write ("[wait 100]\n");
			tc . write ("[Clean]\n");
			tc . write ("[exit]\n\n");
			tc . close ();
		} catch (Exception ex) {return;}
		clean = true;
	}
	public void erase () {
		default_viewport_background = new Colour (0.0, 0.0, 1.0);
		if (viewports != null) viewports . erase (); viewports = null;
		if (tokens != null) tokens . erase (); tokens = null;
		clean = true;
	}
	public boolean moveSelectedTokens (Point delta) {
		Token t = tokens;
		boolean ret = false;
		while (t != null) {
			if (t . selected) {
				t . actionLocation = t . actionLocation . add (delta);
				if (t . moveAction ()) ret = true;
			}
			t = t . next;
		}
		return ret;
	}
	public void hardSelectTokens (Point position) {
		Token t = tokens;
		while (t != null) {if (t . hitTest (position)) t . actionLocation = t . location . position; t = t . next;}
		currentDeck = findDeck (position);
	}
	public DeckToken findDeck (Point p) {
		Token t = tokens;
		while (t != null) {
			if (t instanceof DeckToken && t . locked && t . location . contains (p . add (t . location . size . half ()))) return (DeckToken) t;
			t = t . next;
		}
		return null;
	}
	public DeckToken findSelectedDeck () {
		Token t = tokens;
		while (t != null) {
			if (t instanceof DeckToken & t . locked & t . selected) return (DeckToken) t;
			t = t . next;
		}
		return null;
	}
	public boolean insertSelectedTokensToDeck (DeckToken deck) {
		boolean inserted = false;
		while (tokens != null && tokens . selected) {inserted = true; Token t = tokens . next; deck . insert_token (tokens); tokens = t;}
		Token t = tokens;
		while (t != null) {
			while (t . next != null && t . next . selected) {
				inserted = true; Token tt = t . next . next; deck . insert_token (t . next); t . next = tt;
			}
			t = t . next;
		}
		return inserted;
	}
	public boolean releaseSelectedTokens (Point p) {
		if (currentDeck != null) {Token t = currentDeck . release_token (p); if (t != null) insert_token (t); currentDeck = null; return t != null;}
		DeckToken deck = findDeck (p);
		if (deck != null) {if (insertSelectedTokensToDeck (deck)) return true;}
		Token t = tokens;
		boolean ret = false;
		while (t != null) {if (t . releaseAction ()) ret = true; t = t . next;}
		return ret;
	}
	public boolean doubleAction () {
		Token t = tokens;
		boolean ret = false;
		while (t != null) {if (t . doubleAction ()) ret = true; t = t . next;}
		return ret;
	}
	public void init (PrologRoot root, PrologDirectory directory) {
		this . root = root; this . directory = directory;
		if (location_atom == null && directory != null) {
			location_atom = directory . searchAtom ("Location");
			size_atom = directory . searchAtom ("Size");
			position_atom = directory . searchAtom ("Position");
			scaling_atom = directory . searchAtom ("Scaling");
			repaint_atom = directory . searchAtom ("Repaint");
			rotation_atom = directory . searchAtom ("Rotation");
			rounding_atom = directory . searchAtom ("Rounding");
			side_atom = directory . searchAtom ("Side");
			sides_atom = directory . searchAtom ("Sides");
			foreground_atom = directory . searchAtom ("ForegroundColour");
			background_atom = directory . searchAtom ("BackgroundColour");
			indexing_atom = directory . searchAtom ("Indexing");
			indexed_atom = directory . searchAtom ("Indexed?");
			select_atom = directory . searchAtom ("Select");
			deselect_atom = directory . searchAtom ("Deselect");
			selected_atom = directory . searchAtom ("Selected?");
			lock_atom = directory . searchAtom ("Lock");
			unlock_atom = directory . searchAtom ("Unlock");
			locked_atom = directory . searchAtom ("Locked?");
			roll_atom = directory . searchAtom ("Roll");
			text_atom = directory . searchAtom ("Text");
		}
	}
	public PrologNativeCode getNativeCode (String name) {
		if (name . equals ("Viewport")) return new viewport_class (this, false);
		if (name . equals ("MainViewport")) return new viewport_class (this, true);
		if (name . equals ("Erase")) return new erase_class (this);
		if (name . equals ("Clean")) return new clean_class (this);
		if (name . equals ("Clean?")) return new is_clean_class (this);
		if (name . equals ("SaveBoard")) return new save_board_class (this);
		if (name . equals ("Repaint")) return new repaint_class (this);
		if (name . equals ("Rectangle")) return new rectangle_class (this);
		if (name . equals ("Circle")) return new circle_class (this);
		if (name . equals ("Grid")) return new grid_class (this);
		if (name . equals ("Text")) return new text_class (this);
		if (name . equals ("Picture")) return new picture_class (this);
		if (name . equals ("Dice")) return new dice_class (this);
		if (name . equals ("Deck")) return new deck_class (this);
		if (name . equals ("DefaultBackground")) return new DefaultBackgroundClass (this);
		return null;
	}
	public static void main (String [] args) {
		prologFX . PrologMainFX . initial_size = new Point (0.0, 0.0);
		PrologRoot root = new PrologRoot ();
		root . set_uap32_captions ();
		root . resolution ("fxg");
	}
}

