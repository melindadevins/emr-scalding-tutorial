import matplotlib.pyplot as plt
import pandas as pd

df = pd.DataFrame.from_csv('part-00000', sep='\t', header=None)
df.columns = ["Occurences"]
print(df)

ax = df.plot(kind='bar', title="Stackexchange Age Distribution", figsize=(15, 10), legend=True, fontsize=12)

ax.set_xlabel("Age", fontsize=12)
ax.set_ylabel("Occurences", fontsize=12)
patches, labels = ax.get_legend_handles_labels()
ax.legend(patches, labels, loc='best')

plt.show()