### **Situation**

At CRA, taxpayers submitted dispute forms in unstructured formats like PDFs, Word documents, and scanned images. Manually reviewing these files to classify disputes into categories (e.g., "audit adjustment" or "late filing") was time-consuming and error-prone. 

Misclassification went from 65% to about 10%

---

### **Task**

I was tasked with designing an automated file parser to:

1. Extract text from diverse document types.
2. Categorize disputes into appropriate categories with improved accuracy.
3. Reduce manual intervention and improve efficiency to handle peak loads.

---

### **Action**

#### **1. Transitioning from OpenNLP to SageMaker for NLP**

- Initially implemented **OpenNLP** with Naive Bayes for text tokenization and classification. This provided a quick deployment but was limited by:
    
    - Lack of **contextual understanding** in text classification.
    - Poor handling of **multi-label disputes**, where a dispute belonged to multiple categories.
    - Accuracy of only **72%**, leaving a significant room for improvement.
- Transitioned to **Amazon SageMaker** for fine-tuning a pre-trained **BERT model**. This addressed key limitations:
    
    - **Contextual Understanding**: BERT embeddings captured the semantics of disputes, significantly improving accuracy.
    - **Multi-Label Classification**: Enabled the model to classify disputes into multiple categories when applicable.
    - Improved classification accuracy to **91%**.

---

#### **2. Handling OCR Noise and Preprocessing for NLP**

- Used **Tesseract OCR** for text extraction from scanned documents, PDFs, and images.
- Addressed OCR noise through a preprocessing pipeline integrated into a **Spring Boot microservice**:
    - **Text Normalization**: Removed special characters, standardized date formats, and corrected spelling errors.
    - **Filtering**: Eliminated irrelevant content (e.g., signatures and watermarks) to improve input quality for the NLP model.

---

#### **3. Robust Feedback Loop for Continuous Model Improvement**

- Collaborated with CRA’s support team to identify **domain-specific terms** frequently used in disputes.
- Created a feedback loop where incorrectly categorized disputes were logged, manually reviewed, and added to the training dataset.
- Iteratively retrained the BERT model using **real-world mislabeled data** and additional labeled examples from edge cases.

---

#### **4. Scalable Integration for Peak Traffic**

- Integrated the OCR and NLP pipeline into CRA’s existing **Formal File Dispute (FFD)** system:
    - **SQS queues** for asynchronous processing of disputes.
    - **SageMaker endpoints** hosted with **auto-scaling policies** to handle traffic spikes during peak tax seasons.
    - Ensured a modular design for backward compatibility with existing workflows.

---

### **Result**

1. **Improved Accuracy**:
    - Classification accuracy increased from **65% to 91%**, reducing misclassification rates from **20% to ~5%**.
4. **Scalability**:
    - The system handled **100,000 disputes weekly**, scaling up to **140,000 during peak periods**, with no performance degradation.
5. **Operational Efficiency**:
    - Reduced manual effort by **50%,** saving CRA **~800 staff hours monthly**.

---

### **Technical Summary**

#### **1. OCR Integration**

- Extracted text using **Tesseract OCR** for scanned files and PDFs.
- Preprocessed text to correct spelling errors, normalize formats, and remove irrelevant symbols, ensuring cleaner inputs for the NLP pipeline.

#### **2. SageMaker for NLP**

- Fine-tuned a pre-trained **BERT model** in SageMaker:
    - Trained using **500,000 historical disputes** stored securely in **S3 buckets**.
    - Integrated SageMaker’s **inference endpoint** with the Spring Boot microservice for real-time predictions.
- Implemented **multi-label classification** to handle disputes spanning multiple categories.

#### **3. Feedback Loop and Model Retraining**

- Incorporated misclassified cases into the training dataset for periodic model retraining, ensuring continuous improvement in accuracy.

---

### **Follow-Up Questions Preparation**

1. **Why did you transition from OpenNLP to BERT on SageMaker?**
    
    - _OpenNLP lacked the contextual understanding and multi-label classification capabilities required for complex disputes. SageMaker’s BERT model significantly improved accuracy and scalability._
2. **How did you address OCR noise impacting NLP accuracy?**
    
    - _Used a preprocessing pipeline for text normalization, spelling correction, and filtering irrelevant content._
3. **How did you ensure scalability for peak traffic?**
    
    - _Integrated SQS queues for asynchronous processing and applied auto-scaling policies to SageMaker endpoints._
4. **What was the biggest technical challenge in this project?**
    
    - _Handling vague or multi-label disputes required confidence thresholds and iterative retraining with real-world mislabeled data._
5. **How did you improve model accuracy over time?**
    
    - _Created a feedback loop to retrain the model with additional labeled examples and incorporated domain-specific terminology._
6. **How many disputes did the system handle, and what were the peak volumes?**
    
    - _Handled **100,000 disputes weekly**, scaling to **140,000 disputes during peak seasons** with no degradation in performance._
7. **How did this system improve CRA’s operations?**
    
    - _Automated 70% of categorization tasks, reduced misclassification rates to 5%, and saved 800 staff hours monthly._


### **Leadership Principles and One-Line Justifications**
1. **Customer Obsession**:  
    Improved taxpayer experience by reducing misclassification rates and speeding up dispute resolution times.
2. **Dive Deep**:  
    Identified limitations in OpenNLP and enhanced the solution with SageMaker’s BERT for better accuracy and scalability.
3. **Invent and Simplify**:  
    Replaced manual and error-prone processes with a streamlined OCR-NLP pipeline, leveraging modern AI tools.
4. **Bias for Action**:  
    Quickly transitioned from an OpenNLP-based solution to a more robust SageMaker pipeline to meet CRA’s needs.
    
5. **Insist on the Highest Standards**:  
    Delivered a system with 91% accuracy, reducing misclassification rates from 20% to ~5%, and improving operational efficiency.
    
6. **Think Big**:  
    Designed a scalable architecture to handle peak volumes of 140,000 weekly disputes without degradation.
    
7. **Earn Trust**:  
    Built trust with stakeholders by iteratively improving the system and incorporating their feedback to address edge cases.
    
8. **Deliver Results**:  
    Automated 70% of dispute categorization, reduced resolution times by 30%, and saved 800 staff hours monthly.
    

---

### **Behavioral Questions and One-Line Answers**

1. **Customer Obsession**:
    
    - **Q**: Tell me about a time you improved a system to better meet customer needs.
    - **A**: I reduced misclassification rates to ~5% and sped up dispute resolution by 30%, improving taxpayer satisfaction.
2. **Dive Deep**:
    
    - **Q**: Describe a time you uncovered the root cause of a problem.
    - **A**: I identified OpenNLP’s lack of contextual understanding as the primary reason for misclassifications and upgraded to BERT.
3. **Invent and Simplify**:
    
    - **Q**: How have you simplified a complex process or system?
    - **A**: I replaced a manual process with an AI-driven pipeline that automated 70% of dispute classifications.
4. **Bias for Action**:
    
    - **Q**: Tell me about a time you acted quickly to address a challenge.
    - **A**: I transitioned to SageMaker’s BERT model within weeks, addressing CRA’s misclassification challenges effectively.
5. **Insist on the Highest Standards**:
    
    - **Q**: How have you ensured high-quality results in a project?
    - **A**: I delivered a system with 91% accuracy, reducing misclassification rates from 20% and ensuring robust scalability.
6. **Think Big**:
    
    - **Q**: Describe a time you proposed a bold solution to solve a significant problem.
    - **A**: I designed a scalable architecture to handle 140,000 weekly disputes during peak seasons with no degradation.
7. **Earn Trust**:
    
    - **Q**: How did you gain the trust of stakeholders in a challenging project?
    - **A**: I collaborated closely with the support team, incorporated their feedback, and iteratively improved the system.
8. **Deliver Results**:
    
    - **Q**: How did you ensure the success of a high-impact project?
    - **A**: I automated 70% of tasks, reduced resolution times by 30%, and saved CRA 800 staff hours monthly.