package ChemicalEquations;

public enum Element {
	 H("Hydrogen", 1, 1.008), He("Helium", 2, 4.003), Li("Lithium", 3, 6.941), Be("Beryllium", 4, 9.012),
	 B("Boron", 5, 10.811), C("Carbon", 6, 12.011), N("Nitrogen", 7, 14.007), O("Oxygen", 8, 15.999), 
	 F("Fluorine", 9, 18.998), Ne("Neon", 10, 20.180), Na("Sodium", 11, 22.990), Mg("Magnesium", 12, 24.305),
	 Al("Aluminium", 13, 26.982), Si("Silicon", 14, 28.086), P("Phosphorus", 15, 30.974), S("Sulfur", 16, 32.065),
	 Cl("Chlorine", 17, 35.453), Ar("Argon", 18, 39.946), K("Potassium", 19, 39.096), Ca("Calcium", 20, 40.078),
	 Sc("Scandium", 21, 44.956), Ti("Titanium", 22, 47.867), V("Vanadium", 23, 50.942), Cr("Chromium", 24, 51.996),
	 Mn("Manganese", 25, 54.938), Fe("Iron", 26, 55.845), Co("Cobalt", 27, 58.933), Ni("Nickel", 28, 58.693),
	 Cu("Copper", 29, 63.546), Zn("Zinc", 30, 65.380), Ga("Gallium", 31, 69.723), Ge("Germanium", 32, 72.631),
	 As("Arsenic", 33, 74.922), Se("Selenium", 34, 78.971), Br("Bromine", 35, 79.904), Kr("Krypton", 36, 84.798),
	 Rb("Rubidium", 37, 84.468), Sr("Strontium", 38, 87.620), Y("Yttrium", 39, 88.906), Zr("Zirconium", 40, 91.224),
	 Nb("Niobium", 41, 92.906), Mo("Molybdenum", 42, 95.950), Tc("Technetium", 43, 98.907), Ru("Ruthenium", 44, 101.070),
	 Rh("Rhodium", 45, 102.906), Pd("Palladium", 46, 106.420), Ag("Silver", 47, 107.868), Cd("Cadmium", 48, 112.411),
	 In("Indium", 49, 114.818), Sn("Tin", 50, 118.711), Sb("Antimony", 51, 121.760), Te("Tellurium", 52, 127.600),
	 I("Iodine", 53, 126.904), Xe("Xenon", 54, 131.294), Cs("Cesium", 55, 132.905), Ba("Barium", 56, 137.328),
	 La("Lanthanum", 57, 138.905), Ce("Cerium", 58, 140.116), Pr("Praseodymium", 59, 140.908), Nd("Neodymium", 60, 144.243),
	 Pm("Promethium", 61, 144.913), Sm("Samarium", 62, 150.360), Eu("Europium", 63, 151.964), Gd("Gadolinium", 64, 157.250),
	 Tb("Terbium", 65, 158.925), Dy("Dysprosium", 66, 162.500), Ho("Holmium", 67, 164.930), Er("Erbium", 68, 167.259),
	 Tm("Thulium", 69, 168.934), Yb("Ytterbium", 70, 173.055), Lu("Lutetium", 71, 174.967), Hf("Hafnium", 72, 178.490), 
	 Ta("Tantalum", 73, 180.948), W("Tungsten", 74, 183.840), Re("Rhenium", 75, 186.207), Os("Osmium", 76, 190.230),
	 Ir("Iridium", 77, 192.217), Pt("Platinum", 78, 195.085), Au("Gold", 79, 196.967), Hg("Mercury", 80, 200.592),
	 Tl("Thallium", 81, 204.383), Pb("Lead", 82, 207.200), Bi("Bismuth", 83, 208.982), Po("Polonium", 84, 208.982),
	 At("Astatine", 85, 209.987), Rn("Radon", 86, 222.018), Fr("Francium", 87, 223.020), Ra("Radium", 88, 226.025),
	 Ac("Actinium", 89, 227.028), Th("Thorium", 90, 232.038), Pa("Protactinium", 91, 231.036), U("Uranium", 92, 238.029),
	 Np("Neptunium", 93, 237.048), Pu("Plutonium", 94, 244.064), Am("Americium", 95, 243.061), Cm("Curium", 96, 247.070),
	 Bk("Berkelium", 97, 247.070), Cf("Californium", 98, 251.080), Es("Einsteinium", 99, 252.000), Fm("Fermium", 100, 257.095),
	 Md("Mendelevium", 101, 258.100), No("Nobelium", 102, 259.101), Lr("Lawrencium", 103, 262.000), Rf("Rutherfordium", 104, 261.000),
	 Db("Dubnium", 105, 262.000), Sg("Seaborgium", 106, 266.000), Bh("Bohrium", 107, 264.000), Hs("Hassium", 108, 269.000),
	 Mt("Meitnerium", 109, 268.000), Ds("Darmstadtium", 110, 270.000), Rg("Roentgenium", 111, 272.000), Cn("Copernicium", 112, 285.000),
	 Nh("Nihonium", 113, 286.000), Fl("Flerovium", 114, 289.000), Mc("Moscovium", 115, 288.000), Lv("Livermorium", 116, 298.000),
	 Ts("Tennessine", 117, 294.000), Og("Oganesson", 118, 294.000);
	 
	 String name;
	 int atomicNumber;
	 double atomicWeight;
	 
	 Element(String name, int atomicNumber, double atomicWeight) {
		 this.name = name;
		 this.atomicNumber = atomicNumber;
		 this.atomicWeight = atomicWeight;
	 }
}
