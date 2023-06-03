package k_means2;

//Classe DiscreteItem che estende la classe Item e rappresenta una coppia <Attributo discreto- valore discreto>
public class DiscreteItem extends Item {
	public DiscreteItem(DiscreteAttribute attribute, String value) {
		super(attribute, value);
	}

	@Override
	public double distance(Object a) {
		if (getValue().equals(a))
			return 0.0;
		else
			return 1.0;
	}
}
