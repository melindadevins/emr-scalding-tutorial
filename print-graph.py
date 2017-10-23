import matplotlib.pyplot as plt
import pandas as pd

##
## Merge the output of multiple part-000* files into single file if necessary
## with cat part-000* > scaldingdata.csv
##

df = pd.DataFrame.from_csv('scaldingdata.csv', sep='\t', header=None, index_col=False)

print(df.columns)

df.columns = ['Age', 'Occurences']
df = df.sort_values('Age')

print(df)
ax = df.set_index('Age').plot(kind='bar', title="Stackexchange Age Distribution", figsize=(15, 10), legend=True, fontsize=12)

ax.set_xlabel("Age", fontsize=12)
ax.set_ylabel("Occurences", fontsize=12)

plt.show()