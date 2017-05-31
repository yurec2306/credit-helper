package neuralNetwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.IndividualModel;
import main.IndividualModel.FieldOfActivity;
import main.IndividualModel.MaritialStatus;
import main.IndividualModel.Qualification;

public class NetworkHelper {

	private static Logger logger = LoggerFactory.getLogger(NetworkHelper.class);

	public final static String WEIGHTS_PATH = ".\\src\\main\\resources\\NeuralNetworkSinapsWeights";
	public final static String DELTA_WEIGHTS_PATH = ".\\src\\main\\resources\\deltaWeights";

	private NeuronLayer[] trainSet;
	private ArrayList<ArrayList<Float>> answers = new ArrayList<>();

	public NeuronFirstLayer formToNeuron(IndividualModel model) {
		Neuron[] neuron = new NeuronNoSigmoidImpl[NeuralNetworkImpl.INPUT_LAYER_SIZE];
		for (int i = 0; i < neuron.length; i++) {
			neuron[i] = new NeuronNoSigmoidImpl();
		}
		neuron[0].setData(convertAge(model.getAge()));
		neuron[1].setData(convertMaritialStatus(model.getMaritialStatus()));
		neuron[2].setData(convertChildrenNum(model.getChildrenNum()));
		neuron[3].setData(convertFieldOfActivity(model.getFieldOfActivity()));
		neuron[4].setData(convertQualification(model.getQualification()));
		neuron[5].setData(convertWorkExperience(model.getYearsOfWorkExperience()));
		neuron[6].setData(convertIncome(model.getMonthlyIncome()));
		return new NeuronFirstLayer(neuron);
	}

	private static float convertAge(int age) {
		int result = 0;
		if (age < 26) {
			result = 1;
		} else if (age < 31) {
			result = 2;
		} else if (age < 36) {
			result = 3;
		} else if (age < 51) {
			result = 4;
		} else if (age < 61) {
			result = 5;
		} else {
			result = 0;
		}
		return convert(result, 6f);
	}

	private static float convertMaritialStatus(MaritialStatus maritialStatus) {
		int result = 0;
		switch (maritialStatus) {
		case SINGLE:
			result = 3;
			break;
		case MARRIED:
			result = 4;
			break;
		case MARRIED_BUT_LIVING_SEPARATELY:
			result = 0;
			break;
		case DIVORCED:
			result = 2;
			break;
		case WIDOWER:
			result = 1;
			break;
		}
		return convert(result, 5f);
	}

	private static float convertChildrenNum(int dependentsNum) {
		int result = 0;
		switch (dependentsNum) {
		case 0:
			result = 4;
			break;
		case 1:
			result = 3;
			break;
		case 2:
			result = 2;
			break;
		case 3:
			result = 1;
			break;
		default:
			result = 0;
			break;
		}
		return convert(result, 5f);
	}

	private static float convertFieldOfActivity(FieldOfActivity foa) {
		int result = 0;
		switch (foa) {
		case PRIVATE_SECTOR:
			result = 3;
			break;
		case CIVIL_SERVICE:
			result = 2;
			break;
		case STUDENT:
			result = 1;
			break;
		case PENSIONER:
			result = 0;
			break;
		}
		return convert(result, 4f);
	}

	private static float convertQualification(Qualification qualification) {
		int result = 0;
		switch (qualification) {
		case NO:
			result = 0;
			break;
		case SUPPORT_STAFF:
			result = 1;
			break;
		case SPECIALIST:
			result = 2;
			break;
		case SERVANT:
			result = 3;
			break;
		case SUPERVISOR:
			result = 4;
			break;
		}
		return convert(result, 5f);
	}

	private static float convertWorkExperience(int yearsOfWorkExperience) {
		int result = 0;
		if (yearsOfWorkExperience < 1) {
			result = 0;
		} else if (yearsOfWorkExperience < 2) {
			result = 1;
		} else if (yearsOfWorkExperience < 3) {
			result = 2;
		} else if (yearsOfWorkExperience < 5) {
			result = 3;
		} else {
			result = 4;
		}
		return convert(result, 5f);
	}

	private static float convertIncome(double income) {
		int result = 0;
		if (income < 1_000) {
			result = 0;
		} else if (income < 3_000) {
			result = 1;
		} else if (income < 5_000) {
			result = 2;
		} else if (income < 10_000) {
			result = 3;
		} else {
			result = 4;
		}
		return convert(result, 5f);
	}

	public static float convert(float number, float max) {
		return (number / max);
	}

	public static float[][][] initNetwork(float[][][] networkWeights, String weightsFilepath)
			throws FileNotFoundException, IOException {
		File file = new File(weightsFilepath);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				for (int i = 0; i < networkWeights.length; i++) {
					for (int j = 0; j < networkWeights[i].length; j++) {
						for (int k = 0; k < networkWeights[i][j].length; k++) {
							networkWeights[i][j][k] = ois.readFloat();
						}
					}
				}
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}
		} else {
			file.createNewFile();
			Random random = new Random();
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
				for (int i = 0; i < networkWeights.length; i++) {
					for (int j = 0; j < networkWeights[i].length; j++) {
						for (int k = 0; k < networkWeights[i][j].length; k++) {
							networkWeights[i][j][k] = random.nextFloat();
							oos.writeFloat(networkWeights[i][j][k]);
						}
					}
				}
				oos.flush();
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}
		}
		return networkWeights;
	}

	public static float[][][] reinitNetwork(float[][][] networkWeights, String weightsFilepath)
			throws FileNotFoundException, IOException {
		File file = new File(weightsFilepath);
		if (file.exists())
			file.delete();
		file.createNewFile();
		Random random = new Random();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			for (int i = 0; i < networkWeights.length; i++) {
				for (int j = 0; j < networkWeights[i].length; j++) {
					for (int k = 0; k < networkWeights[i][j].length; k++) {
						networkWeights[i][j][k] = random.nextFloat() - 0.5f;
						oos.writeFloat(networkWeights[i][j][k]);
					}
				}
			}
			oos.flush();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return networkWeights;
	}

	public static void saveNetwork(float[][][] networkWeights, String weightsFilepath) throws FileNotFoundException, IOException {
		File file = new File(weightsFilepath);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			for (int i = 0; i < networkWeights.length; i++) {
				for (int j = 0; j < networkWeights[i].length; j++) {
					for (int k = 0; k < networkWeights[i][j].length; k++) {
						oos.writeFloat(networkWeights[i][j][k]);
					}
				}
			}
			oos.flush();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	public void readTrainSetFromFile(String filepath) throws IOException {
		File file = new File(filepath);
		List<NeuronLayer> trainSet = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = in.readLine()) != null) {
				NeuronFirstLayer inputNeurons = new NeuronFirstLayer(NeuralNetworkImpl.INPUT_LAYER_SIZE);
				String[] temp = line.split(" ");
				ArrayList<Float> tempList = new ArrayList<>(NeuralNetworkImpl.OUTPUT_LAYER_SIZE);
				for (int i = 0; i < inputNeurons.size() + NeuralNetworkImpl.OUTPUT_LAYER_SIZE; i++) {
					if (i < inputNeurons.size()) {
						inputNeurons.setNeuron(i, new NeuronNoSigmoidImpl());
						inputNeurons.getNeuron(i).setData(Float.parseFloat(temp[i]));
					} else {
						tempList.add(Float.parseFloat(temp[i]));
					}
				}
				trainSet.add(inputNeurons);
				this.answers.add(tempList);
			}
		} catch (IOException e) {
			throw e;
		}
		this.trainSet = trainSet.toArray(new NeuronFirstLayer[trainSet.size()]);
	}

	public NeuronLayer[] getTrainSet() {
		return this.trainSet;
	}

	public float[][] getAnswers() {
		float[][] result = new float[this.answers.size()][];

		for (int i = 0; i < this.answers.size(); i++) {
			result[i] = new float[this.answers.get(i).size()];
		}

		for (int i = 0; i < this.answers.size(); i++) {
			for (int j = 0; j < this.answers.get(i).size(); j++) {
				result[i][j] = this.answers.get(i).get(j);
			}
		}

		return result;
	}

	public static float[][][] initDeltaW(float[][][] deltaw, String filepath) throws IOException {
		File file = new File(filepath);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				for (int i = 0; i < deltaw.length; i++) {
					for (int j = 0; j < deltaw[i].length; j++) {
						for (int k = 0; k < deltaw[i][j].length; k++) {
							deltaw[i][j][k] = ois.readFloat();
						}
					}
				}
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}
		} else {
			file.createNewFile();
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
				for (int i = 0; i < deltaw.length; i++) {
					for (int j = 0; j < deltaw[i].length; j++) {
						for (int k = 0; k < deltaw[i][j].length; k++) {
							deltaw[i][j][k] = 0;
							oos.writeFloat(deltaw[i][j][k]);
						}
					}
				}
				oos.flush();
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}
		}
		return deltaw;
	}

	public static float[][][] reinitDeltaW(float[][][] deltaw, String filepath) throws IOException {
		File file = new File(filepath);
		if (file.exists())
			file.delete();
		file.createNewFile();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			for (int i = 0; i < deltaw.length; i++) {
				for (int j = 0; j < deltaw[i].length; j++) {
					for (int k = 0; k < deltaw[i][j].length; k++) {
						oos.writeFloat(deltaw[i][j][k]);
					}
				}
			}
			oos.flush();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return deltaw;
	}

	public static float[][][] saveDeltaW(float[][][] deltaw, String filepath) throws IOException {
		File file = new File(filepath);
		if (file.exists()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
				for (int i = 0; i < deltaw.length; i++) {
					for (int j = 0; j < deltaw[i].length; j++) {
						for (int k = 0; k < deltaw[i][j].length; k++) {
							oos.writeFloat(deltaw[i][j][k]);
						}
					}
				}
				oos.flush();
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}
		}
		return deltaw;
	}

}