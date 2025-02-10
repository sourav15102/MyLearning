### **Rolling Deployment Explained**

#### **What Is Rolling Deployment?**

A **rolling deployment** is a strategy where an application is updated **incrementally**, replacing old versions of the application with new ones **gradually**, without downtime.

The **key idea** is to:

1. Start deploying the new version to a small subset of pods or instances.
2. Gradually replace the old version with the new one.
3. Ensure the application remains functional throughout the process.

#### **How Rolling Deployment Works:**

1. **Initial State:**
    - Assume you have **5 pods** running the old version (`v1`) of your application in Kubernetes.
2. **Step 1:** Deploy 1 Pod with the New Version (`v2`):
    - Kubernetes creates **1 new pod** with `v2` while keeping the other 4 pods running `v1`.
    - Kubernetes directs some traffic to the `v2` pod and monitors its health using **readiness probes**.
3. **Step 2:** Replace Another Pod:
    - Once `v2` is verified as healthy, Kubernetes **terminates 1 `v1` pod** and creates another pod with `v2`.
    - Now, there are **2 pods with `v2`** and **3 pods with `v1`**.
4. **Step 3:** Repeat Until All Pods Run `v2`:
    - This process continues until all pods run `v2`.

#### **Benefits of Rolling Deployment:**

- **No Downtime:** The old version remains active while the new version is deployed incrementally.
- **Rollback Capability:** If something goes wrong, Kubernetes can stop the deployment and revert back to `v1` pods.
- **Smaller Blast Radius:** If the new version has issues, only the updated pods are affected.