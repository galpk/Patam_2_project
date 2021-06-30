package model;

import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public  class Commands {

	// Default IO interface
	public interface DefaultIO {
		public String readText();

		public void write(String text);

		public float readVal();

		public void write(float val);

		// you may add default methods here
	}

	// the default IO to be used in all commands
	DefaultIO dio;

	public Commands(DefaultIO dio) {
		this.dio = dio;
	}

	// you may add other helper classes here


	// the shared state of all commands
	private class SharedState {
		TimeSeries train = null;

		TimeSeries test = null;

		List<AnomalyReport> ListAnomalyR = null;

		List<Point> ListOfPoints = null;

	}

	private SharedState sharedState = new SharedState();

	public SharedState getSharedState() {
		return sharedState;
	}

	public DefaultIO getDio() {
		return dio;
	}

	// Command abstract class
	public abstract static class Command {
		protected String description;

		public Command(String description) {
			this.description = description;
		}

		public abstract void execute();
	}

	// Command class for example:
	public class ExampleCommand extends Command {

		public ExampleCommand() {
			super("this is an example of command");
		}

		@Override
		public void execute() {
			dio.write(description);
		}
	}

	public class MenuCommand extends Command {
		public MenuCommand() {
			super("Welcome to the Anomaly Detection Server.\n" +
					"Please choose an option:\n" +
					"1. upload a time series csv file\n" +
					"2. algorithm settings\n" +
					"3. detect anomalies\n" +
					"4. display results\n" +
					"5. upload anomalies and analyze results\n" +
					"6. exit\n");
		}

		@Override
		public void execute() {
			dio.write(description);

			String readSelection = dio.readText();
			int selection = Integer.parseInt(readSelection);
			switch (selection) {
				case 1:
					new UploadCSVFileCommand().execute();
					break;

				case 2:  // algo settings
					new algoSettingsCommand().execute();
					break;

				case 3:  // detect
					new detectAnomaliesCommand().execute();
					break;

				case 4:  // display
					new displayResultsCommand().execute();
					break;

				case 5:  // upload anomalies and analyze
					new uploadAndAnalyzeCommand().execute();
					break;

				case 6:  // exit
					new exitCommand().execute();
					break;
			}
		}
	}

		public class UploadCSVFileCommand extends Command {
		public UploadCSVFileCommand() {
			super("Please upload your local train CSV file.\n");
		}

		@Override
		public void execute() {

			// First File //
			dio.write(description);
			String line = null;
			ArrayList<String> read = new ArrayList<>();
			while (!(line = dio.readText()).equals("done")) {
				read.add(line);
				read.add("\n");
			}
			try {
				FileWriter CSVFile = new FileWriter("anomalyTrain.csv");
				String All = read.stream().collect(Collectors.joining(""));
				CSVFile.write(All);
				CSVFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			getSharedState().train = new TimeSeries("anomalyTrain.csv");
			dio.write("Upload complete.\n");

			// Second File //
			dio.write("Please upload your local test CSV file.\n");
			String line2 = null;
			ArrayList<String> read2 = new ArrayList<>();
			while (!(line2 = dio.readText()).equals("done")) {
				read2.add(line2);
				read2.add("\n");
			}
			try {
				FileWriter CSVFile = new FileWriter("anomalyTest.csv");
				String All2 = read2.stream().collect(Collectors.joining(""));
				CSVFile.write(All2);
				CSVFile.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			getSharedState().test = new TimeSeries("anomalyTest.csv");
			dio.write("Upload complete.\n");
			new MenuCommand().execute();
		}
	}

		public class algoSettingsCommand extends Command {

		String msg;
		double threshold;

		public algoSettingsCommand() {

			super("");
			threshold = getSharedState().train.correlationThresh;
			msg = "The current correlation threshold is ";
			msg = msg.concat(String.valueOf(threshold));
			msg = msg.concat("\nType a new threshold\n");
		}

		@Override
		public void execute() {
			dio.write(msg);
			double tempTresh = Double.parseDouble(dio.readText());
			while (tempTresh > 1 || tempTresh < 0) {
				dio.write("please choose a value between 0 and 1.");
				tempTresh = Double.parseDouble(dio.readText());

			}

			getSharedState().train.SetcorrelationThresh(tempTresh);
			new MenuCommand().execute();

		}

	}

		public class detectAnomaliesCommand extends Command{

		public detectAnomaliesCommand()
		{
			super("anomaly detection complete.\n");
		}
	TimeSeries ts;
		@Override
		public void execute()
		{

			SimpleAnomalyDetector SAD = new SimpleAnomalyDetector(ts);

			SAD.learnNormal(getSharedState().train);

			List<AnomalyReport> tempList = new ArrayList<>();

			tempList.addAll(SAD.detect(getSharedState().test));

			getSharedState().ListAnomalyR = tempList;

			dio.write(description);

			new MenuCommand().execute();

		}
	}

		public class displayResultsCommand extends Command {

			public displayResultsCommand() {
				super("Done.\n");
			}


			@Override
			public void execute() {
				for (AnomalyReport ar : getSharedState().ListAnomalyR)
					dio.write((String.valueOf(ar.timeStep)) + "\t" + ar.description + "\n");
				dio.write(description);
				new MenuCommand().execute();
			}
		}

		public class uploadAndAnalyzeCommand extends Command {

			public uploadAndAnalyzeCommand() {
				super("Please upload your local anomalies file.\n" + "Upload complete.\n");
			}

			@Override
			public void execute() {

				dio.write(description);
				float rangeSum = 0;
				String line = null;
				getSharedState().ListOfPoints = new ArrayList<>();
				while (!((line = dio.readText()).equals("done"))) {
					String[] temp = line.split(",");
					getSharedState().ListOfPoints.add(new Point(Float.valueOf(temp[0]), Float.valueOf(temp[1])));

				}

				for (Point p : getSharedState().ListOfPoints)
					rangeSum += p.y - p.x + 1;
				float N = getSharedState().test.Dtable.get(0).Vlist.size() - rangeSum;
				ArrayList<String> discription = new ArrayList<>();
				ArrayList<Point> time = new ArrayList<>();
				int q = 0;
				if (getSharedState().ListAnomalyR == null)
					return;

				for (int i = 0; i < getSharedState().ListAnomalyR.size(); i++) {
					AnomalyReport start = getSharedState().ListAnomalyR.get(i);
					AnomalyReport end = null;
					int j = i + 1;
					while (j < getSharedState().ListAnomalyR.size()
							&& start.description.equals(getSharedState().ListAnomalyR.get(j).description)
							&& start.timeStep + j - q == getSharedState().ListAnomalyR.get(j).timeStep) {
						end = getSharedState().ListAnomalyR.get(j);
						j++;

					}

					if (end != null) {
						discription.add(end.description);
						time.add(new Point(start.timeStep, end.timeStep));
					} else {
						discription.add(start.description);
						time.add(new Point(start.timeStep, start.timeStep));
					}
					q = j;
					i = j - 1;
				}

				float FP = 0;
				float TP = 0;
				float TN = 0;
				float FN = 0;
				float P = getSharedState().ListOfPoints.size();
				boolean flag = false;
				List<Point> pointList = new ArrayList<>();
				for (Point p : getSharedState().ListOfPoints) {
					for (Point k : time) {
						if ((p.x <= k.x && k.y <= p.y)
								|| (p.x >= k.x && p.y >= k.y && k.y >= p.x)
								|| (k.x <= p.x && p.y <= k.y)
								|| (p.x <= k.x && p.y <= k.y && k.x <= p.y)) {
							if (!flag)
								TP++;
							flag = true;
							if (!pointList.contains(k))
								pointList.add(k);
						}
					}

					if (!flag)
						FN++;

					flag = false;
				}
				FP = (time.size() - pointList.size()) / N;

				TP /= P;

				DecimalFormat decimalFormat = new DecimalFormat("0.0000");
				String tp = decimalFormat.format(TP);
				String fp = decimalFormat.format(FP);
				tp = tp.substring(0, tp.length() - 1);
				fp = fp.substring(0, fp.length() - 1);
				while (tp.charAt(tp.length() - 1) == '0' && tp.length() != 3)
					tp = tp.substring(0, tp.length() - 1);

				while (fp.charAt(fp.length() - 1) == '0' && fp.length() != 3)
					fp = fp.substring(0, fp.length() - 1);

				dio.write("True Positive Rate: " + tp + "\n");
				dio.write("False Positive Rate: " + fp + "\n");
				new MenuCommand().execute();

			}
		}

		public class exitCommand extends Command {

			public exitCommand() {
				super("");
			}
			@Override
			public void execute() {
				dio.write(description);
			}
		}


}
