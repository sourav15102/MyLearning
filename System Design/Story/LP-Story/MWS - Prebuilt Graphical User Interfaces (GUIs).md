#### **Situation**

While creating a data analysis pipeline for internal use, I leveraged MATLAB's **Curve Fitting Tool (CFTOOL)** to allow users to perform quick visual fitting of experimental data. The tool worked perfectly during development but needed to be packaged and deployed to a larger team.

#### **Why the Request Couldn't Be Fulfilled**

When attempting to compile the application using MATLAB Compiler, I found that **prebuilt GUIs like CFTOOL** were unsupported. The tool could not be included in the deployed application.

#### **How I Managed It**

To address this, I developed a custom GUI using MATLAB's `uifigure` and `fit` functions, mimicking CFTOOL’s core functionalities. This approach ensured compatibility with the deployed environment while meeting the team’s requirements.

#### **Result**

The custom GUI was successfully deployed, and users appreciated the simplified interface tailored to their specific needs. It also highlighted the importance of identifying such limitations early in the development cycle.