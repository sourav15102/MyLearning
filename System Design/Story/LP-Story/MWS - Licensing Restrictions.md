#### **Situation**

In a collaborative project to streamline real-time data visualization, I planned to use a licensed **figure manipulation tool** in MATLAB to allow runtime adjustments of plots within a compiled application. This would let users interactively analyze live data during simulations.

#### **Why the Request Couldn't Be Fulfilled**

The specific tool was restricted by licensing policies, making it non-deployable in compiled applications. This meant users couldn’t interactively manipulate figures in the deployed version.

#### **How I Managed It**

To overcome this, I implemented a workaround using MATLAB’s `uicontrol` elements to build interactive controls for zooming, panning, and scaling plots. I also added an option to save plots for offline analysis.

#### **Result**

Although less feature-rich than the licensed tool, the custom solution provided sufficient interactivity for the project’s needs. It also minimized dependency on non-deployable components, ensuring future compatibility.