class WeightedQuickUnionUF :
	parent = []
	size = []
	def __init__(self, n):
		self.count = n
		self.parent = [i for i in range(n)]
		self.size = [1 for _ in range(n)]

	def count(self):
		return self.count

	def find(self, p):
		self._validate(p)
		while(p != self.parent[p]):
			p = self.parent[p]
		return p

	def _validate (self, p) :

		n = len(self.parent)
		if p < 0 or p >= n :
			raise ValueError

	def connected(self, p, q) :
		return self.find(p) == self.find(q)
		
	def union(self, p, q) :
		rootP = self.find(p)
		rootQ = self.find(q)
		if (rootP == rootQ) :
			return

		if (self.size[rootP] > self.size[rootQ]) :
			self.parent[rootQ] = rootP
			self.size[rootP] += self.size[rootQ]

		else :
			self.parent[rootP] = rootQ
			self.size[rootQ] += self.size[rootP] 
		self.count -= 1


	def get(self):
		print(self.parent, self.size)




